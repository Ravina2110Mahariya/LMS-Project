package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "course_content")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseContent {

    @Id
    private String id;

    private String courseId;

    private String title;

    private String description;

    private String videoUrl;

    private String driveUrl;

    private String fileUrl;

    private String fileName;

    private String type;
}