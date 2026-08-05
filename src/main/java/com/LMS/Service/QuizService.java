package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Quiz;
import com.LMS.Repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    public Quiz getById(String id) {
        return quizRepository.findById(id).orElse(null);
    }

    public void delete(String id) {
        quizRepository.deleteById(id);
    }

}