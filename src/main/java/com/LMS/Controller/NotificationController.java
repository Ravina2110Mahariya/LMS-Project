package com.LMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.Entity.Notification;
import com.LMS.Service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    // SEND NOTIFICATION
    @PostMapping("/send")
    public ResponseEntity<?> send(
            @RequestBody Notification notification) {

        String admin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        notification.setCreatedBy(admin);

        return ResponseEntity.ok(
                service.send(notification)
        );
    }

    // MY NOTIFICATIONS
    @GetMapping("/my")
    public ResponseEntity<List<Notification>> my() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(
                service.myNotifications(email)
        );
    }

    // ALL NOTIFICATIONS
    @GetMapping("/all")
    public ResponseEntity<List<Notification>> all() {

        return ResponseEntity.ok(
                service.all()
        );
    }
}