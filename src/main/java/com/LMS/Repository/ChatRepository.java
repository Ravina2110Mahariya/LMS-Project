package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.ChatMessage;

public interface ChatRepository
        extends MongoRepository<ChatMessage, String> {

    List<ChatMessage>
    findByCourseId(String courseId);
}