package com.LMS.dto;

import lombok.Data;

@Data
public class DashboardStats {

    private long totalCourses;
    private long enrolledCourses;
    private long assignments;
    private long certificates;
    private long liveClasses;
}