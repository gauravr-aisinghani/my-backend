package com.example.controller;

import com.example.dto.ApiResponse;
import com.example.dto.LoginRequest; // create if you don't have
import com.example.entity.Client;
import com.example.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ClientRepository repo;
    private final PasswordEncoder encoder;

    public AuthController(ClientRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req, HttpSession session) {
        Optional<Client> opt = repo.findByEmail(req.getEmail());
        if (opt.isEmpty()) return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid credentials"));

        Client c = opt.get();
        if (!c.isVerified()) {
            return ResponseEntity.status(403).body(new ApiResponse(false, "Account is deactivated"));
        }

        if (!encoder.matches(req.getPassword(), c.getPassword())) {
            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid credentials"));
        }

        // login success -> set session attributes
        session.setAttribute("CLIENT_ID", c.getId());
        session.setAttribute("CLIENT_EMAIL", c.getEmail());
        session.setAttribute("ROLE", c.getRole());
        session.setMaxInactiveInterval(60); // 10 minutes

        return ResponseEntity.ok(new ApiResponse(true, "Login successful"));
    }
    
    
    

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(new ApiResponse(true, "Logged out"));
    }

    @GetMapping("/session-status")
    public ResponseEntity<?> sessionStatus(HttpSession session) {
        Object role = session.getAttribute("ROLE");
        if (role == null) return ResponseEntity.status(401).body(new ApiResponse(false, "No active session"));
        return ResponseEntity.ok(new ApiResponse(true, "Active session for " + role.toString()));
    }
}
