package com.LMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnrollmentDTO {

    private String courseTitle;
    private String description;
    private String status;
}