package com.LMS.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "announcements")
public class Announcement {

    @Id
    private String id;

    private String title;

    private String message;

    private String createdBy;

    private LocalDateTime createdAt =
            LocalDateTime.now();
}