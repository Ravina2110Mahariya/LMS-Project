package com.LMS.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "assignment_submissions")
public class AssignmentSubmission {

    @Id
    private String id;

    private String assignmentId;
    private String studentEmail;
    private String answer;
    private String status;
    private Integer marks;
}