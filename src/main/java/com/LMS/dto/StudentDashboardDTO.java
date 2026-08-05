package com.LMS.dto;

import lombok.Data;

@Data
public class StudentDashboardDTO {
	private String student;

	private long totalCourses;

	private long enrolledCourses;
	
	private long totalAssignments;
	
	private long totalCertificates;

	private long notifications;

	private long submissions;

	private long completedCourses;

	private long liveClasses;
    }