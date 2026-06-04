package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.AssignmentSubmission;

public interface AssignmentSubmissionRepository
        extends MongoRepository<AssignmentSubmission, String> {

    List<AssignmentSubmission> findByAssignmentId(
            String assignmentId);

    List<AssignmentSubmission> findByStudentEmail(
            String studentEmail);
}