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

    // =========================
    // SAVE / SEND NOTIFICATION
    // =========================
    public Notification save(Notification notification) {
        return repo.save(notification);
    }

    public Notification send(Notification notification) {
        return repo.save(notification);
    }

    // =========================
    // GET ALL NOTIFICATIONS
    // =========================
    public List<Notification> getAll() {
        return repo.findAll();
    }

    public List<Notification> all() {
        return repo.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    public Notification getById(String id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notification Not Found"));
    }

    // =========================
    // DELETE
    // =========================
    public void delete(String id) {
        repo.deleteById(id);
    }

    // =========================
    // STUDENT NOTIFICATIONS
    // =========================
    public List<Notification> myNotifications(String email) {
        return repo.findByStudentEmail(email);
    }
}