package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Notification;

public interface NotificationRepository
        extends MongoRepository<Notification, String> {

    List<Notification> findByStudentEmail(
            String studentEmail);
}