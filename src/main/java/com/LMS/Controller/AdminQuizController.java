package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Quiz;
import com.LMS.Repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/quizzes")
@RequiredArgsConstructor
public class AdminQuizController {

    private final QuizRepository quizRepository;

    @GetMapping
    public String quizList(Model model) {

        model.addAttribute("quizzes",
                quizRepository.findAll());

        return "admin/quiz-list";
    }

    @GetMapping("/add")
    public String addQuizPage(Model model) {

        model.addAttribute("quiz",
                new Quiz());

        return "admin/add-quiz";
    }

    @PostMapping("/save")
    public String saveQuiz(
            @ModelAttribute Quiz quiz) {

        quizRepository.save(quiz);

        return "redirect:/admin/quizzes";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuiz(
            @PathVariable String id) {

        quizRepository.deleteById(id);

        return "redirect:/admin/quizzes";
    }
}