package com.example.controller.admin;

import com.example.dto.ApiResponse;
import com.example.dto.ClientResponse;
import com.example.dto.CreateClientRequest;
import com.example.dto.UpdateClientRequest;
import com.example.entity.Client;
import com.example.service.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/clients")
public class ClientAdminController {

    private final ClientService clientService;

    public ClientAdminController(ClientService clientService) {
        this.clientService = clientService;
    }

    private boolean isSuperAdmin(HttpSession session) {
        Object role = session.getAttribute("ROLE");
        return role != null && role.equals("ROLE_SUPER_ADMIN");
    }

    private ResponseEntity<ApiResponse> forbidden() {
        return ResponseEntity.status(403)
                .body(new ApiResponse(false, "Forbidden"));
    }

    // ------------------- CREATE CLIENT ---------------------
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody CreateClientRequest req, HttpSession session) {
        if (!isSuperAdmin(session)) return forbidden();

        try {
            Client c = clientService.createClient(req);
            return ResponseEntity.ok(toResponse(c));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
        }
    }

    // ------------------- LIST CLIENTS ---------------------
    @GetMapping
    public ResponseEntity<?> listClients(HttpSession session) {
        if (!isSuperAdmin(session)) return forbidden();

        List<ClientResponse> list = clientService.listAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    // ------------------- GET CLIENT BY ID ---------------------
    @GetMapping("/{id}")
    public ResponseEntity<Object> getClient(@PathVariable Long id, HttpSession session) {

        if (!isSuperAdmin(session))
            return ResponseEntity.status(403)
                    .body(new ApiResponse(false, "Forbidden"));

        return clientService.findById(id)
                .map(client -> ResponseEntity.ok((Object) toResponse(client)))
                .orElseGet(() ->
                        ResponseEntity.status(404)
                                .body(new ApiResponse(false, "Client not found"))
                );
    }


    // ------------------- UPDATE CLIENT ---------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(
            @PathVariable Long id,
            @RequestBody UpdateClientRequest req,
            HttpSession session) {

        if (!isSuperAdmin(session)) return forbidden();

        try {
            Client updated = clientService.updateClient(id, req);
            return ResponseEntity.ok(toResponse(updated));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
        }
    }

    // ------------------- RESET PASSWORD ---------------------
    @PutMapping("/{id}/reset-password")
    public ResponseEntity<?> resetPassword(
            @PathVariable Long id,
            @RequestParam String newPassword,
            HttpSession session) {

        if (!isSuperAdmin(session)) return forbidden();

        try {
            clientService.resetPassword(id, newPassword);
            return ResponseEntity.ok(new ApiResponse(true, "Password reset"));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
        }
    }

    // ------------------- DELETE CLIENT ---------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id, HttpSession session) {
        if (!isSuperAdmin(session)) return forbidden();

        clientService.deleteClient(id);
        return ResponseEntity.ok(new ApiResponse(true, "Deleted"));
    }

    // ------------------- CONVERTER ---------------------
    private ClientResponse toResponse(Client c) {
        ClientResponse r = new ClientResponse();
        r.setId(c.getId());
        r.setEmail(c.getEmail());
        r.setCompanyName(c.getCompanyName());
        r.setPhone(c.getPhone());
        r.setVerified(c.isVerified());
        r.setRole(c.getRole());
        r.setCreatedAt(c.getCreatedAt());
        return r;
    }
}
