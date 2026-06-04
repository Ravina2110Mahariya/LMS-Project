package com.LMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Assignment;
import com.LMS.Service.AssignmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/assignment")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService service;

    // ✅ ADD ASSIGNMENT
    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody Assignment assignment) {

        return ResponseEntity.ok(
                service.addAssignment(assignment)
        );
    }

    // ✅ GET ASSIGNMENTS
    @GetMapping("/{courseId}")
    public ResponseEntity<List<Assignment>> getByCourse(
            @PathVariable String courseId) {

        return ResponseEntity.ok(
                service.getByCourse(courseId)
        );
    }
}