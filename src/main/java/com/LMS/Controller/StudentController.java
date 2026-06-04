package com.LMS.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/student/dashboard")
    public String studentDashboard() {

        return "Student Dashboard";
    }
}