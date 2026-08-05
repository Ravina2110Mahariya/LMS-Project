package com.LMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.Entity.AssignmentSubmission;
import com.LMS.Service.SubmissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/submission")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService service;

    //  STUDENT SUBMIT ASSIGNMENT
    @PostMapping("/submit")
    public ResponseEntity<?> submit(
            @RequestBody AssignmentSubmission submission) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        submission.setStudentEmail(email);

        return ResponseEntity.ok(
                service.submit(submission)
        );
    }

    //  GET SUBMISSIONS BY ASSIGNMENT
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<AssignmentSubmission>>
    getByAssignment(
            @PathVariable String assignmentId) {

        return ResponseEntity.ok(
                service.getByAssignment(assignmentId)
        );
    }

    //  GET MY SUBMISSIONS
    @GetMapping("/my")
    public ResponseEntity<List<AssignmentSubmission>>
    mySubmissions() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(
                service.getByStudent(email)
        );
    }

    //  GIVE MARKS
    @PutMapping("/marks/{submissionId}")
    public ResponseEntity<?> giveMarks(
            @PathVariable String submissionId,
            @RequestParam Integer marks) {

        try {

            AssignmentSubmission updated =
                    service.giveMarks(
                            submissionId,
                            marks
                    );

            return ResponseEntity.ok(updated);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}