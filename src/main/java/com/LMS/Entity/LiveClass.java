package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "live_classes")
public class LiveClass {

    @Id
    private String id;

    private String courseId;

    private String title;

    private String description;

    private String meetingLink;

    private String classDate;

    private String startTime;

    private String instructorName;
}