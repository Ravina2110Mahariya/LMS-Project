package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentPageController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "student/dashboard";
    }

    @GetMapping("/courses")
    public String courses() {
        return "student/courses";
    }

    @GetMapping("/my-courses")
    public String myCourses() {
        return "student/my-courses";
    }

    @GetMapping("/quizzes")
    public String quizzes() {
        return "student/quizzes";
    }

    @GetMapping("/assignments")
    public String assignments() {
        return "student/assignments";
    }

    @GetMapping("/progress")
    public String progress() {
        return "student/progress";
    }

    @GetMapping("/certificates")
    public String certificates() {
        return "student/certificates";
    }

    @GetMapping("/live-classes")
    public String liveClasses() {
        return "student/live-classes";
    }

    @GetMapping("/notes")
    public String notes() {
        return "student/notes";
    }

    @GetMapping("/chat")
    public String chat() {
        return "student/chat";
    }

    @GetMapping("/profile")
    public String profile() {
        return "student/profile";
    }
}