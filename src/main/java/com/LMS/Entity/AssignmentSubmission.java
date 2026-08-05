package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "assignment_submissions")
public class AssignmentSubmission {

    @Id
    private String id;

    private String assignmentId;
    private String studentEmail;
    private String submissionUrl;
    
    private String answer;
    private Integer marks;
    private String status;
    private String feedback;
}