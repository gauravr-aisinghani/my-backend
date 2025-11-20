package com.example;

import com.example.entity.Client;
import com.example.repository.ClientRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DynamicCrudApplication {

    public static void main(String[] args) { SpringApplication.run(DynamicCrudApplication.class, args); }

    // Auto-create default super-admin if not present (change credentials immediately)
    @Bean
    public ApplicationRunner runner(ClientRepository repo, PasswordEncoder encoder) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) {
                String adminEmail = "admin@wtl.com";
                String adminPasswordPlain = "Admin@123"; // CHANGE THIS in production
                if (repo.findByEmail(adminEmail).isEmpty()) {
                    Client admin = new Client();
                    admin.setEmail(adminEmail);
                    admin.setCompanyName("WTL-Admin");
                    admin.setPassword(encoder.encode(adminPasswordPlain));
                    admin.setVerified(true);
                    admin.setRole("ROLE_SUPER_ADMIN");
                    repo.save(admin);
                    System.out.println("Default super-admin created: " + adminEmail + " / " + adminPasswordPlain);
                }
            }
        };
    }
}
