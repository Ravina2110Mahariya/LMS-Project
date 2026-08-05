package com.LMS.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "reports")
public class Report {

    @Id
    private String id;

    private String reportName;

    private String reportType;

    private String generatedBy;

    private LocalDateTime generatedAt =
            LocalDateTime.now();
}