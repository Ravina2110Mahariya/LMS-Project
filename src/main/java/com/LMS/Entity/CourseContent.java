package com.LMS.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "course_content")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseContent {

    @Id
    private String id;

    private String courseId;

    private String title;
    private String type; // VIDEO / PDF

    private String fileUrl; // path or URL
    
    private String fileName;
}