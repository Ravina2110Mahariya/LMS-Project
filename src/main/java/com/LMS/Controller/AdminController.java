package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.LMS.Entity.Course;
import com.LMS.Repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CourseRepository courseRepository;

    @GetMapping("/courses/add")
    public String addCourseForm(Model model) {

        model.addAttribute("course", new Course());

        return "admin/add-course";
    }

    @PostMapping("/courses/save")
    public String saveCourse(@ModelAttribute Course course) {

        courseRepository.save(course);

        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/edit/{id}")
    public String editCourseForm(
            @PathVariable String id,
            Model model) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        model.addAttribute("course", course);

        return "admin/edit-course";
    }

    @PostMapping("/courses/update")
    public String updateCourse(
            @ModelAttribute Course course) {

        courseRepository.save(course);

        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(
            @PathVariable String id) {

        courseRepository.deleteById(id);

        return "redirect:/admin/courses";
    }
}