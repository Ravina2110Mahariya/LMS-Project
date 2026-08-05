package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "assignments")
public class Assignment {

    @Id
    private String id;
    private String courseId;
    private String title;
    private String description;
    private String dueDate;
    private String fileUrl;
    private String fileName;
    
}