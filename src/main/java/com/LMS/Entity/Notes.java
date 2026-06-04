package com.LMS.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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