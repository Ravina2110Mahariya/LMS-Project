package com.LMS.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "chat_messages")
public class ChatMessage {

    @Id
    private String id;

    private String sender;

    private String receiver;

    private String courseId;

    private String message;

    private LocalDateTime sentAt =
            LocalDateTime.now();
}