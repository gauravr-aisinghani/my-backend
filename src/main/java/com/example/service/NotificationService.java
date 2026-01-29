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

    // ================= ADMIN (OLD - safe) =================
    public void notifyAdmins(String title, String message) {

        Notification n = new Notification();
        n.setRole(Role.ADMIN);
        n.setUserId("ADMIN");
        n.setTitle(title);
        n.setMessage(message);

        notificationRepository.save(n);
        messagingTemplate.convertAndSend("/topic/admin", n);
    }

    // ================= ADMIN (NEW - DRIVER REQUEST) =================
    public void notifyAdmins(
            String title,
            String message,
            String type,
            Long referenceId
    ) {

        Notification n = new Notification();
        n.setRole(Role.ADMIN);
        n.setUserId("ADMIN");
        n.setTitle(title);
        n.setMessage(message);
        n.setType(type);
        n.setReferenceId(referenceId);

        notificationRepository.save(n);
        messagingTemplate.convertAndSend("/topic/admin", n);
    }

    // ================= TRANSPORTER (OLD - keep as is) =================
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

    // ================= TRANSPORTER (NEW - WITH reference_id) =================
    public void notifyTransporter(
            String mobile,
            String title,
            String message,
            String type,
            Long referenceId
    ) {

        Notification n = new Notification();
        n.setRole(Role.TRANSPORTER);
        n.setUserId(mobile);
        n.setTitle(title);
        n.setMessage(message);
        n.setType(type);
        n.setReferenceId(referenceId); // 🔥 yahi missing tha

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
