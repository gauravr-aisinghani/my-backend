//package com.example.controller;
//
//import com.example.dto.ApiResponse;
//import com.example.dto.LoginRequest;
//import com.example.entity.Client;
//import com.example.repository.ClientRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.servlet.http.HttpSession;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final ClientRepository repo;
//    private final PasswordEncoder encoder;
//
//    public AuthController(ClientRepository repo, PasswordEncoder encoder) {
//        this.repo = repo;
//        this.encoder = encoder;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest req, HttpSession session) {
//        Optional<Client> opt = repo.findByEmail(req.getEmail());
//        if (opt.isEmpty())
//            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid credentials"));
//
//        Client c = opt.get();
//
//        if (!c.isVerified())
//            return ResponseEntity.status(403).body(new ApiResponse(false, "Account is deactivated"));
//
//        if (!encoder.matches(req.getPassword(), c.getPassword()))
//            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid credentials"));
//
//        // ----------------------------
//        // LOGIN SUCCESS — SET SESSION
//        // ----------------------------
//        session.setAttribute("CLIENT_ID", c.getId());
//        session.setAttribute("CLIENT_EMAIL", c.getEmail());
//        session.setAttribute("ROLE", c.getRole());
//
//        session.setMaxInactiveInterval(60); // ⏳ 1 minute (for testing auto logout)
//
//        return ResponseEntity.ok(new ApiResponse(true, "Login successful"));
//    }
//
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpSession session) {
//        session.invalidate();
//        return ResponseEntity.ok(new ApiResponse(true, "Logged out"));
//    }
//
//
//    @GetMapping("/session-status")
//    public ResponseEntity<?> sessionStatus(HttpSession session) {
//
//        Object role = session.getAttribute("ROLE");
//
//        if (role == null)
//            return ResponseEntity.status(401).body(new ApiResponse(false, "No active session"));
//
//        int ttl = session.getMaxInactiveInterval(); // seconds left
//
//        return ResponseEntity.ok(new ApiResponse(true, "Active session (" + role + "), TTL=" + ttl + "s"));
//    }
//}



package com.example.controller;

import com.example.dto.ApiResponse;

import com.example.dto.LoginRequest;
import com.example.entity.Client;
import com.example.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ClientRepository repo;
    private final PasswordEncoder encoder;

    // ----------------- CONFIG -----------------
    // 10 minutes = 600 seconds
    private static final int SESSION_TIMEOUT_SECONDS = 10 * 60;

    public AuthController(ClientRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // -------------------------------------------
    //               LOGIN
    // -------------------------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req, HttpSession session) {

        Optional<Client> opt = repo.findByEmail(req.getEmail());
        if (opt.isEmpty()) {
            return ResponseEntity.status(401).body(
                    new ApiResponse(false, "Invalid credentials"));
        }

        Client c = opt.get();

        if (!c.isVerified()) {
            return ResponseEntity.status(403).body(
                    new ApiResponse(false, "Account is deactivated"));
        }

        if (!encoder.matches(req.getPassword(), c.getPassword())) {
            return ResponseEntity.status(401).body(
                    new ApiResponse(false, "Invalid credentials"));
        }

        // ----------------------------
        // LOGIN SUCCESS — CREATE SESSION
        // ----------------------------
        session.setAttribute("CLIENT_ID", c.getId());
        session.setAttribute("CLIENT_EMAIL", c.getEmail());
        session.setAttribute("ROLE", c.getRole());

        session.setMaxInactiveInterval(SESSION_TIMEOUT_SECONDS);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Login successful",
                "role", c.getRole(),      // ADMIN
                "userId", c.getId()       // admin unique id
        ));

    }

    // -------------------------------------------
    //        SESSION STATUS + REMAINING TIME
    // -------------------------------------------
    @GetMapping("/session-status")
    public ResponseEntity<?> sessionStatus(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("ROLE") == null) {
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "message", "No active session"
            ));
        }

        // Calculate remaining seconds
        long now = System.currentTimeMillis();
        long lastAccess = session.getLastAccessedTime();
        int maxInactive = session.getMaxInactiveInterval();

        long remainingSeconds = maxInactive - ((now - lastAccess) / 1000);

        if (remainingSeconds < 0) remainingSeconds = 0;

        return ResponseEntity.ok(Map.of(
                "success", true,
                "role", session.getAttribute("ROLE"),
                "ttl", maxInactive,
                "remainingSeconds", remainingSeconds
        ));
    }

    // -------------------------------------------
    //          EXTEND SESSION (Add 10 min)
    // -------------------------------------------
    @GetMapping("/extend-session")
    public ResponseEntity<?> extendSession(HttpSession session) {

        if (session == null || session.getAttribute("ROLE") == null) {
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "message", "No active session"
            ));
        }

        session.setMaxInactiveInterval(SESSION_TIMEOUT_SECONDS);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Session extended",
                "ttl", session.getMaxInactiveInterval()
        ));
    }

    // -------------------------------------------
    //                   LOGOUT
    // -------------------------------------------
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // ❗ Completely removes session + deletes cookie
        }

        return ResponseEntity.ok(
                new ApiResponse(true, "Logged out")
        );
    }
}
