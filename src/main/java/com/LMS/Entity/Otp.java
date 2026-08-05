package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "otp")
public class Otp {

    @Id
    private String id;

    private String email;

    private String otp;
}