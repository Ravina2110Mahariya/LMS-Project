package com.LMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LMS.Entity.Quiz;
import com.LMS.Repository.QuizRepository;

@Controller
public class StudentQuizController {

    @Autowired
    private QuizRepository quizRepository;

    @PostMapping("/student/quiz/submit")
    public String submitQuiz(
            @RequestParam String quizId,
            @RequestParam String answer,
            Model model) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow();

        boolean correct =
                quiz.getCorrectAnswer()
                        .equalsIgnoreCase(answer);

        model.addAttribute("question",
                quiz.getQuestion());

        model.addAttribute("yourAnswer",
                answer);

        model.addAttribute("correctAnswer",
                quiz.getCorrectAnswer());

        model.addAttribute("result",
                correct
                        ? "Correct "
                        : "Wrong ");

        return "student/quiz-result";
    }
}