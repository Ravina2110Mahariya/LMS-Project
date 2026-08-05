package com.LMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.Entity.Discussion;
import com.LMS.Service.DiscussionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/discussion")
@RequiredArgsConstructor
public class DiscussionController {

    private final DiscussionService service;

    // =========================
    // STUDENT ASK QUESTION
    // =========================
    @PostMapping("/ask")
    public ResponseEntity<?> askQuestion(
            @RequestBody Discussion discussion) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        discussion.setStudentEmail(email);

        return ResponseEntity.ok(
                service.askQuestion(discussion)
        );
    }

    // =========================
    // GET ALL DISCUSSIONS
    // =========================
    @GetMapping("/all")
    public ResponseEntity<List<Discussion>> getAll() {

        return ResponseEntity.ok(
                service.getAll()
        );
    }

    // =========================
    // GET QUESTIONS BY COURSE
    // =========================
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Discussion>> getByCourse(
            @PathVariable String courseId) {

        return ResponseEntity.ok(
                service.getByCourse(courseId)
        );
    }

    // =========================
    // MY QUESTIONS
    // =========================
    @GetMapping("/my")
    public ResponseEntity<List<Discussion>> myQuestions() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(
                service.myQuestions(email)
        );
    }

    // =========================
    // ADMIN REPLY
    // =========================
    @PutMapping("/reply/{id}")
    public ResponseEntity<?> reply(

            @PathVariable String id,
            @RequestParam String reply

    ) {

        String admin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(
                service.reply(id, reply, admin)
        );
    }
}