package com.LMS.dto;

import lombok.Data;

@Data
public class StudentDashboardDTO {

    private String student;

    private long enrolledCourses;

    private long completedCourses;

    private long certificates;

    private long notifications;

    private long submissions;
}