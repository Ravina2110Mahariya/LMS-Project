package com.LMS.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.ChatMessage;
import com.LMS.Service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService service;

    // SEND MESSAGE
    @PostMapping("/send")
    public ChatMessage send(
            @RequestBody ChatMessage message) {

        return service.send(message);
    }

    // COURSE CHAT
    @GetMapping("/course/{courseId}")
    public List<ChatMessage> getChat(
            @PathVariable String courseId) {

        return service.getCourseChat(
                courseId
        );
    }
}