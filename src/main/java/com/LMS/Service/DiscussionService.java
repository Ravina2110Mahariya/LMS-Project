package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Discussion;
import com.LMS.Repository.DiscussionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscussionService {

    private final DiscussionRepository repo;

    // =========================
    // ASK QUESTION
    // =========================
    public Discussion askQuestion(
            Discussion discussion) {

        return repo.save(discussion);
    }

    // =========================
    // GET QUESTIONS BY COURSE
    // =========================
    public List<Discussion> getByCourse(
            String courseId) {

        return repo.findByCourseId(courseId);
    }

    // =========================
    // GET MY QUESTIONS
    // =========================
    public List<Discussion> myQuestions(
            String email) {

        return repo.findByStudentEmail(email);
    }

    // =========================
    // GET ALL QUESTIONS
    // =========================
    public List<Discussion> getAll() {

        return repo.findAll();
    }

    // =========================
    // ADMIN REPLY
    // =========================
    public Discussion reply(
            String id,
            String reply,
            String admin) {

        Discussion d = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Question not found"));

        d.setReply(reply);

        d.setRepliedBy(admin);

        return repo.save(d);
    }
}