package com.LMS.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {

    private String studentEmail;
    private String courseId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}