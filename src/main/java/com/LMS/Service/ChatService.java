package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.ChatMessage;
import com.LMS.Repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository repo;

    // SEND MESSAGE
    public ChatMessage send(
            ChatMessage message) {

        return repo.save(message);
    }

    // GET COURSE CHAT
    public List<ChatMessage>
    getCourseChat(String courseId) {

        return repo.findByCourseId(
                courseId
        );
    }
}