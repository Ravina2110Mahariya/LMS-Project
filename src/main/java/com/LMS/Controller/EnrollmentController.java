package com.LMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.LMS.Entity.Enrollment;
import com.LMS.Service.EnrollmentService;
import com.LMS.dto.EnrollmentDTO;

@Controller
@RequestMapping("/enroll")
public class EnrollmentController {

    @Autowired
    private EnrollmentService service;

    // =========================
    // ENROLL COURSE
    // =========================
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    @PostMapping
    public String enroll(
            @RequestParam String courseId) {

        Enrollment e = new Enrollment();
        e.setCourseId(courseId);

        service.enroll(e);

        return "redirect:/student/my-courses";
    }

    // =========================
    // MY COURSES API
    // =========================
    @ResponseBody
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    @GetMapping("/my-courses")
    public List<EnrollmentDTO> getMyCourses() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return service.getMyCoursesByEmail(email);
    }
}