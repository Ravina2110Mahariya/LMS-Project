package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "videos")
public class VideoLecture {

    @Id
    private String id;

    private String title;

    private String courseId;

    private String videoName;

    private String videoPath;

    private String uploadedBy;
}