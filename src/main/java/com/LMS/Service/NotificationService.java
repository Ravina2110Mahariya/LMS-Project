package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Notification;
import com.LMS.Repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repo;

    // SEND NOTIFICATION
    public Notification send(
            Notification notification) {

        return repo.save(notification);
    }

    // MY NOTIFICATIONS
    public List<Notification> myNotifications(
            String email) {

        return repo.findByStudentEmail(email);
    }

    // ALL NOTIFICATIONS
    public List<Notification> all() {

        return repo.findAll();
    }
}