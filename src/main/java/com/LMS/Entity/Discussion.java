package com.LMS.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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