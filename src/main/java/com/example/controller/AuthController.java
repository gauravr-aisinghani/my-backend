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

    // ----------------- CONFIGURE THESE VALUES -----------------
    // Production desired session length (seconds) -> 10 minutes
    private static final int PROD_SESSION_SECONDS = 10 * 60;

    // Testing session length (seconds) -> 1 minute
    private static final int TEST_SESSION_SECONDS = 60;

    // Toggle: set to true to use testing TTL (1 minute). Set to false for production TTL (10 minutes).
    // You can also wire these from application.properties, but this simple constant is convenient for testing.
    private static final boolean USE_TEST_TTL = true;
    // ---------------------------------------------------------

    private int chosenTtlSeconds() {
        return USE_TEST_TTL ? TEST_SESSION_SECONDS : PROD_SESSION_SECONDS;
    }

    public AuthController(ClientRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

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

        // ----------------------------
        // LOGIN SUCCESS — SET SESSION
        // ----------------------------
        session.setAttribute("CLIENT_ID", c.getId());
        session.setAttribute("CLIENT_EMAIL", c.getEmail());
        session.setAttribute("ROLE", c.getRole());

        // set MaxInactiveInterval according to chosen TTL.
        // FOR TESTING: USE_TEST_TTL=true => 60 seconds
        // FOR PROD: set USE_TEST_TTL=false => 600 seconds (10 minutes)
        session.setMaxInactiveInterval(chosenTtlSeconds());

        return ResponseEntity.ok(new ApiResponse(true, "Login successful"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // ---- Force cookie deletion in browser (important for cross-site cookies) ----
        // We add both a Cookie object and a Set-Cookie header with SameSite=None to be explicit.
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0); // delete immediately
        // Note: Cookie API does not have a direct setSameSite method in some environments;
        // therefore we also add the header below to enforce SameSite=None.
        response.addCookie(cookie);

        // Add explicit Set-Cookie header (ensures SameSite=None is present)
        // Format: JSESSIONID=; Path=/; Max-Age=0; HttpOnly; Secure; SameSite=None
        response.addHeader("Set-Cookie",
                "JSESSIONID=; Path=/; Max-Age=0; HttpOnly; Secure; SameSite=None");

        return ResponseEntity.ok(new ApiResponse(true, "Logged out"));
    }

    /**
     * Returns remaining TTL in seconds, and the role.
     * Frontend should use "ttl" to keep its timer accurate.
     */
    @GetMapping("/session-status")
    public ResponseEntity<?> sessionStatus(HttpSession session) {

        Object role = (session == null) ? null : session.getAttribute("ROLE");

        if (role == null)
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "message", "No active session"
            ));

        // session.getMaxInactiveInterval() returns seconds remaining until invalidation
        int ttl = session.getMaxInactiveInterval();

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Active session (" + role + ")",
                "role", role.toString(),
                "ttl", ttl
        ));
    }

    /**
     * Extend session (used when user clicks "Extend session").
     * This will reset session TTL to the configured chosenTtlSeconds().
     *
     * Endpoint: GET /api/auth/extend-session
     */
    @GetMapping("/extend-session")
    public ResponseEntity<?> extendSession(HttpSession session, HttpServletResponse response) {
        Object role = (session == null) ? null : session.getAttribute("ROLE");

        if (role == null)
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "message", "No active session"
            ));

        // Reset session TTL to configured value
        session.setMaxInactiveInterval(chosenTtlSeconds());

        // Optionally refresh cookie expiration by re-sending cookie (helps some browsers)
        // We send a Set-Cookie header with same attributes to refresh cookie on client side.
        response.addHeader("Set-Cookie",
                "JSESSIONID=" + session.getId() + "; Path=/; Max-Age=" + chosenTtlSeconds()
                        + "; HttpOnly; Secure; SameSite=None");

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Session extended",
                "ttl", session.getMaxInactiveInterval()
        ));
    }
}

