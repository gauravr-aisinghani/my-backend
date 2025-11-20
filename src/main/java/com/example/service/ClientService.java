package com.example.service;

import com.example.dto.CreateClientRequest;
import com.example.dto.UpdateClientRequest;
import com.example.entity.Client;
import com.example.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repo;
    private final PasswordEncoder encoder;

    public ClientService(ClientRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public Client createClient(CreateClientRequest req) {
        Optional<Client> existing = repo.findByEmail(req.getEmail());
        if (existing.isPresent()) throw new IllegalArgumentException("Email already exists");
        Client c = new Client();
        c.setEmail(req.getEmail());
        c.setPassword(encoder.encode(req.getPassword()));
        c.setCompanyName(req.getCompanyName());
        c.setPhone(req.getPhone());
        c.setVerified(req.isActive());
        c.setRole("ROLE_CLIENT");
        return repo.save(c);
    }

    public List<Client> listAll() { return repo.findAll(); }

    public Optional<Client> findById(Long id) { return repo.findById(id); }

    public Client updateClient(Long id, UpdateClientRequest req) {
        Client c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        if (req.getCompanyName() != null) c.setCompanyName(req.getCompanyName());
        if (req.getPhone() != null) c.setPhone(req.getPhone());
        if (req.getActive() != null) c.setVerified(req.getActive());
        return repo.save(c);
    }

    public Client resetPassword(Long id, String newPassword) {
        Client c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        c.setPassword(encoder.encode(newPassword));
        return repo.save(c);
    }

    public void deleteClient(Long id) {
        repo.deleteById(id);
    }
}
