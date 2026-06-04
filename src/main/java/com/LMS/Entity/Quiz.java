package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "quiz")
public class Quiz {

    @Id
    private String id;

    private String courseId;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String correctAnswer;
}