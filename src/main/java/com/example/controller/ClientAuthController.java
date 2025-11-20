package com.example.controller;

import com.example.dto.ApiResponse;

import com.example.dto.LoginRequest;
import com.example.entity.Client;
import com.example.repository.ClientRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientAuthController {

    private final ClientRepository repo;
    private final PasswordEncoder encoder;

    public ClientAuthController(ClientRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // ⭐ CLIENT LOGIN ONLY
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req, HttpSession session) {

        Optional<Client> opt = repo.findByEmail(req.getEmail());

        if (opt.isEmpty()) {
            return ResponseEntity.status(401)
                    .body(new ApiResponse(false, "Email not registered"));
        }

        Client c = opt.get();

        // Block Super Admin
        if ("ROLE_SUPER_ADMIN".equals(c.getRole())) {
            return ResponseEntity.status(403)
                    .body(new ApiResponse(false, "Admins cannot login here"));
        }

        // ❗ Check verified (using isVerified())
        if (!c.isVerified()) {
            return ResponseEntity.status(403)
                    .body(new ApiResponse(false, "Your account is not verified by admin"));
        }

        // Wrong password
        if (!encoder.matches(req.getPassword(), c.getPassword())) {
            return ResponseEntity.status(401)
                    .body(new ApiResponse(false, "Incorrect password"));
        }

        // SUCCESS → create session
        session.setAttribute("CLIENT_ID", c.getId());
        session.setAttribute("CLIENT_EMAIL", c.getEmail());
        session.setAttribute("ROLE", c.getRole());
        session.setMaxInactiveInterval(600);

        return ResponseEntity.ok(new ApiResponse(true, "Login successful"));
    }

    
    // ⭐ LOGOUT (destroy session)
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session, HttpServletResponse response) {

        session.invalidate(); // destroy session

        // Remove cookie	
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // true if using HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(0); // expire immediately
        response.addCookie(cookie);

        return ResponseEntity.ok(new ApiResponse(true, "Logout successful"));
    }


    // ⭐ CHECK SESSION STATUS
    @GetMapping("/session-status")
    public ResponseEntity<?> sessionStatus(HttpSession session) {

        Object id = session.getAttribute("CLIENT_ID");

        if (id == null) {
            return ResponseEntity.ok(new ApiResponse(false, "Session expired or not logged in"));
        }

        return ResponseEntity.ok(new ApiResponse(true, "Session active"));
    }
}
