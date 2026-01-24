package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.entity.Notification;
import com.example.entity.Notification.Role;
import com.example.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    // ================= ADMIN =================
    public void notifyAdmins(String title, String message) {

        // 1️⃣ DB insert (offline safe)
        Notification n = new Notification();
        n.setRole(Role.ADMIN);
        n.setUserId("ADMIN"); // ya admin email later
        n.setTitle(title);
        n.setMessage(message);
        notificationRepository.save(n);

        // 2️⃣ WebSocket (online)
        messagingTemplate.convertAndSend("/topic/admin", n);
    }

    // ================= TRANSPORTER =================
    public void notifyTransporter(String mobile, String title, String message) {

        Notification n = new Notification();
        n.setRole(Role.TRANSPORTER);
        n.setUserId(mobile);
        n.setTitle(title);
        n.setMessage(message);
        notificationRepository.save(n);

        messagingTemplate.convertAndSend(
                "/queue/transporter/" + mobile,
                n
        );
    }

    // ================= DRIVER =================
    public void notifyDriver(String mobile, String title, String message) {

        Notification n = new Notification();
        n.setRole(Role.DRIVER);
        n.setUserId(mobile);
        n.setTitle(title);
        n.setMessage(message);
        notificationRepository.save(n);

        messagingTemplate.convertAndSend(
                "/queue/driver/" + mobile,
                n
        );
    }
}
