package com.LMS.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "assignments")
public class Assignment {

    @Id
    private String id;

    private String courseId;
    private String title;
    private String description;
    private String fileUrl;
}