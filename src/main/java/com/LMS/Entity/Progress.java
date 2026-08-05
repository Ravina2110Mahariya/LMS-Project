package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Progress {

    @Id
    private String id;
    private String courseName;
    private String userId;
    private String courseId;

    private String studentEmail;
    private int completedLessons;
    private int totalLessons;

    private double percentage;
}