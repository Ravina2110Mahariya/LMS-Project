package com.LMS.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Progress {

    @Id
    private String id;

    private String userId;
    private String courseId;

    private int completedLessons;
    private int totalLessons;

    private double percentage;
}