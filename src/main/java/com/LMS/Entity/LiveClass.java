package com.LMS.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "live_classes")
public class LiveClass {

    @Id
    private String id;

    private String title;

    private String courseId;

    private String meetingLink;

    private String date;

    private String time;

    private String instructor;
}