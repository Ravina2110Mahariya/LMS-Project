package com.LMS.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "discussions")
public class Discussion {

    @Id
    private String id;

    private String courseId;

    private String studentEmail;

    private String question;

    private String reply;

    private String repliedBy;

    private LocalDateTime createdAt =
            LocalDateTime.now();
}