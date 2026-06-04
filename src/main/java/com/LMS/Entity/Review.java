package com.LMS.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "reviews")
public class Review {

    @Id
    private String id;

    private String courseId;

    private String studentEmail;

    private int rating; // 1 to 5

    private String comment;

    private LocalDateTime createdAt;
}