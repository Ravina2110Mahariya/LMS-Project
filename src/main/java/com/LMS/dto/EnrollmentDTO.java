package com.LMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {

    private String id;

    private String title;

    private String description;

    private String category;

    private String status;
}