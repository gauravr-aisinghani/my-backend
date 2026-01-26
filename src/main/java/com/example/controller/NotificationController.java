package com.example.controller;

import com.example.entity.Notification;
import com.example.repository.NotificationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository repository;

    public NotificationController(NotificationRepository repository) {
        this.repository = repository;
    }

    // =============== ADMIN =================
    @GetMapping("/admin/{adminId}")
    public List<Notification> getAdminNotifications(
            @PathVariable String adminId
    ) {
        return repository.findByUserIdAndRoleAndIsReadFalse(
                adminId,
                Notification.Role.ADMIN
        );
    }

    // =============== TRANSPORTER =================
    @GetMapping("/transporter/{mobile}")
    public List<Notification> getTransporterNotifications(
            @PathVariable String mobile
    ) {
        return repository.findByUserIdAndRoleAndIsReadFalse(
                mobile,
                Notification.Role.TRANSPORTER
        );
    }

    // =============== DRIVER =================
    @GetMapping("/driver/{mobile}")
    public List<Notification> getDriverNotifications(
            @PathVariable String mobile
    ) {
        return repository.findByUserIdAndRoleAndIsReadFalse(
                mobile,
                Notification.Role.DRIVER
        );
    }

    // =============== COMMON =================
    @PostMapping("/mark-read/{notificationId}")
    public void markAsRead(@PathVariable Long notificationId) {
        repository.findById(notificationId).ifPresent(n -> {
            n.setIsRead(true);
            repository.save(n);
        });
    }
}

