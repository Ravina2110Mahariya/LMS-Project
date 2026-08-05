package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.CourseContent;
import com.LMS.Service.CourseContentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/course-content")
@RequiredArgsConstructor
public class AdminCourseContentController {

    private final CourseContentService courseContentService;

    @GetMapping("")
    public String list(Model model) {

        model.addAttribute(
                "contents",
                courseContentService.getAll());

        return "admin/course-content-list";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute(
                "content",
                new CourseContent());

        return "admin/add-course-content";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute CourseContent content) {

        courseContentService.save(content);

        return "redirect:/admin/course-content";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "content",
                courseContentService.getById(id));

        return "admin/add-course-content";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id) {

        courseContentService.delete(id);

        return "redirect:/admin/course-content";
    }
}