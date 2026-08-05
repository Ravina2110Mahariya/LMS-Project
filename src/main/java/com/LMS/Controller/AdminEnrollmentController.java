package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Enrollment;
import com.LMS.Service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/enrollments")
@RequiredArgsConstructor
public class AdminEnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping("")
    public String list(Model model) {

        model.addAttribute(
                "enrollments",
                enrollmentService.getAll());

        return "admin/enrollment-list";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute(
                "enrollment",
                new Enrollment());

        return "admin/add-enrollment";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Enrollment enrollment) {

        enrollmentService.save(enrollment);

        return "redirect:/admin/enrollments";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "enrollment",
                enrollmentService.getById(id));

        return "admin/add-enrollment";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id) {

        enrollmentService.delete(id);

        return "redirect:/admin/enrollments";
    }

}