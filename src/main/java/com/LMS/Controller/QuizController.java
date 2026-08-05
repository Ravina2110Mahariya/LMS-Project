package com.LMS.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.Entity.Quiz;
import com.LMS.Repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizRepository repo;

    // =========================
    // ADD QUIZ
    // =========================
    @PostMapping("/add")
    public ResponseEntity<?> addQuiz(
            @RequestBody Quiz quiz) {

        return ResponseEntity.ok(
                repo.save(quiz)
        );
    }

    // =========================
    // GET QUIZ BY COURSE ID
    // =========================
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Quiz>> getQuizByCourse(
            @PathVariable String courseId) {

        return ResponseEntity.ok(
                repo.findByCourseId(courseId)
        );
    }

    // =========================
    // GET QUIZ BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(
            @PathVariable String id) {

        return ResponseEntity.ok(

                repo.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Quiz not found"
                                ))
        );
    }

    // =========================
    // SUBMIT QUIZ
    // =========================
    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(

            @RequestParam String quizId,
            @RequestParam String answer) {

        Quiz quiz = repo.findById(quizId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Quiz not found"
                        ));

        boolean correct =
                quiz.getCorrectAnswer()
                        .equalsIgnoreCase(answer);

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "question",
                quiz.getQuestion()
        );

        response.put(
                "yourAnswer",
                answer
        );

        response.put(
                "correctAnswer",
                quiz.getCorrectAnswer()
        );

        response.put(
                "result",
                correct
                        ? "Correct ✅"
                        : "Wrong ❌"
        );

        return ResponseEntity.ok(response);
    }

     // =========================
    // QUIZ my-result
    // =========================

    @GetMapping("/my-result")
    public ResponseEntity<?> myResult() {

        Map<String, Object> result =
                new HashMap<>();

        result.put("student", "ravina@gmail.com");
        result.put("score", 1);
        result.put("status", "PASS ");

        return ResponseEntity.ok(result);
    }
}