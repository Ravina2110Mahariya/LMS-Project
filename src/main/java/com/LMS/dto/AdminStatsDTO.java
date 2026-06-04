package com.LMS.dto;

import lombok.Data;

@Data
public class AdminStatsDTO {

    private long students;
    private long courses;
    private long enrollments;
    private long assignments;
    private long certificates;
    private long quizzes;
}