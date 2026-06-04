package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.AssignmentSubmission;

public interface SubmissionRepository
        extends MongoRepository<AssignmentSubmission, String> {

    // =========================
    // FIND BY ASSIGNMENT
    // =========================
    List<AssignmentSubmission> findByAssignmentId(
            String assignmentId
    );

    // =========================
    // FIND BY STUDENT EMAIL
    // =========================
    List<AssignmentSubmission> findByStudentEmail(
            String studentEmail
    );
}