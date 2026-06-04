package com.LMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Enrollment;
import com.LMS.Service.EnrollmentService;
import com.LMS.dto.EnrollmentDTO;

@RestController
@RequestMapping("/enroll")
public class EnrollmentController {

    @Autowired
    private EnrollmentService service;

    // ✅ ENROLL COURSE
    // STUDENT + ADMIN
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    @PostMapping
    public ResponseEntity<?> enroll(@RequestBody Enrollment e) {

        try {

            return ResponseEntity.ok(
                    service.enroll(e)
            );

        } catch (RuntimeException ex) {

            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    // ✅ GET MY COURSES
    // STUDENT + ADMIN
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