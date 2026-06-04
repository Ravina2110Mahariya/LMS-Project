package com.LMS.dto;

import lombok.Data;

@Data
public class QuizSubmitRequest {

    private String quizId;
    private String selectedAnswer;
}