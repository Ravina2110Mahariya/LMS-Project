package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "notes")
public class Notes {

    @Id
    private String id;

    private String title;

    private String courseId;

    private String fileName;

    private String filePath;

    private String uploadedBy;
}