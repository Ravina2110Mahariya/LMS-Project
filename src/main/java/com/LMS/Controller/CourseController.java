package com.LMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.Entity.Course;
import com.LMS.Service.CourseService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    // ✅ GET ALL COURSES (PUBLIC)
    @GetMapping
    public Page<Course> getCourses(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "title")
            String sortBy) {

        return service.getCourses(
                page,
                size,
                sortBy
        );
    }
    

    // ✅ GET COURSE BY ID (PUBLIC)
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id) {

        return service.getCourseById(id);
    }

    // ✅ ADD COURSE (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Course addCourse(@RequestBody Course course) {

        return service.addCourse(course);
    }

    // ✅ UPDATE COURSE (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable String id,
                               @RequestBody Course course) {

        return service.updateCourse(id, course);
    }

    // ✅ DELETE COURSE (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable String id) {

        return service.deleteCourse(id);
    }
}