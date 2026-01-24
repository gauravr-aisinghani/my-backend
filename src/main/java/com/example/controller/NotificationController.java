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

    // GET unread notifications for admin
    @GetMapping("/admin/{adminId}")
    public List<Notification> getAdminNotifications(@PathVariable String adminId) {
        return repository.findByUserIdAndIsReadFalse(adminId);
    }

    // Optional: mark notifications as read
    @PostMapping("/admin/mark-read/{notificationId}")
    public void markAsRead(@PathVariable Long notificationId) {
        repository.findById(notificationId).ifPresent(n -> {
            n.setIsRead(true);
            repository.save(n);
        });
    }
}
