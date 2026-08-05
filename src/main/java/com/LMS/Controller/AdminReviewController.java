package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Review;
import com.LMS.Service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/reviews")
@RequiredArgsConstructor
public class AdminReviewController {

    private final ReviewService service;

    @GetMapping("")
    public String list(Model model) {

        model.addAttribute("reviews", service.getAll());

        return "admin/review-list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {

        service.delete(id);

        return "redirect:/admin/reviews";
    }

}