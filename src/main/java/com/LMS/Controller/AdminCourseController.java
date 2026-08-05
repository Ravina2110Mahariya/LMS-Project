package com.LMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.LMS.Entity.Course;
import com.LMS.Service.CourseService;

@Controller
@RequestMapping("/admin")
public class AdminCourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/course/add")
    public String addCoursePage(Model model) {

        model.addAttribute("course", new Course());

        return "admin/add-course";
    }

    @PostMapping("/course/save")
    public String saveCourse(@ModelAttribute Course course) {

        courseService.addCourse(course);

        return "redirect:/admin/courses";
    }
} 