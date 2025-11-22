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
import jakarta.servlet.http.Cookie;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ClientRepository repo;
    private final PasswordEncoder encoder;

    // ------------------------
    // SESSION TIMING SETTINGS
    // ------------------------

    // Production = 10 min = 600 sec
    private static final int PROD_SESSION_SECONDS = 10 * 60;

    // Testing = 1 min = 60 sec
    private static final int TEST_SESSION_SECONDS = 60;

    // true → 60 sec (testing)
    // false → 600 sec (production)
    private static final boolean USE_TEST_TTL = true;

    private int chosenTtlSeconds() {
        return USE_TEST_TTL ? TEST_SESSION_SECONDS : PROD_SESSION_SECONDS;
    }

    public AuthController(ClientRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // ------------------------
    // LOGIN
    // ------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req, HttpSession session) {
        Optional<Client> opt = repo.findByEmail(req.getEmail());
        if (opt.isEmpty())
            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid credentials"));

        Client c = opt.get();

        if (!c.isVerified())
            return ResponseEntity.status(403).body(new ApiResponse(false, "Account is deactivated"));

        if (!encoder.matches(req.getPassword(), c.getPassword()))
            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid credentials"));

        // SET SESSION ATTRIBUTES
        session.setAttribute("CLIENT_ID", c.getId());
        session.setAttribute("CLIENT_EMAIL", c.getEmail());
        session.setAttribute("ROLE", c.getRole());

        // APPLY SESSION TTL
        session.setMaxInactiveInterval(chosenTtlSeconds());

        return ResponseEntity.ok(new ApiResponse(true, "Login successful"));
    }

    // ------------------------
    // LOGOUT
    // ------------------------
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Delete JSESSIONID cookie
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        // Force deletion with SameSite=None
        response.addHeader("Set-Cookie",
                "JSESSIONID=; Path=/; Max-Age=0; HttpOnly; Secure; SameSite=None");

        return ResponseEntity.ok(new ApiResponse(true, "Logged out"));
    }

    // ------------------------
    // SESSION STATUS (REAL REMAINING TIME)
    // ------------------------
    @GetMapping("/session-status")
    public ResponseEntity<?> sessionStatus(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null)
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "message", "No active session"
            ));

        Object role = session.getAttribute("ROLE");
        if (role == null)
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "message", "No active session"
            ));

        long now = System.currentTimeMillis();
        long lastAccess = session.getLastAccessedTime();
        long expiryTime = lastAccess + (session.getMaxInactiveInterval() * 1000L);

        long remainingSeconds = Math.max(0, (expiryTime - now) / 1000);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "role", role.toString(),
                "remainingSeconds", remainingSeconds
        ));
    }

    // ------------------------
    // EXTEND SESSION
    // ------------------------
    @GetMapping("/extend-session")
    public ResponseEntity<?> extendSession(HttpSession session, HttpServletResponse response) {

        if (session == null || session.getAttribute("ROLE") == null)
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "message", "No active session"
            ));

        // Reset session TTL
        session.setMaxInactiveInterval(chosenTtlSeconds());

        // Refresh cookie expiration
        response.addHeader("Set-Cookie",
                "JSESSIONID=" + session.getId() +
                        "; Path=/; Max-Age=" + chosenTtlSeconds() +
                        "; HttpOnly; Secure; SameSite=None");

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Session extended",
                "ttl", session.getMaxInactiveInterval()
        ));
    }
}
