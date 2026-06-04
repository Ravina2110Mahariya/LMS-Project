package com.LMS.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "attendance")
public class Attendance {

    @Id
    private String id;

    private String studentEmail;

    private String courseId;

    private String date;

    private String status;
}