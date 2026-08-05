package com.LMS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.LMS.Entity.AssignmentSubmission;
import com.LMS.Repository.AssignmentSubmissionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignmentSubmissionService {

    private final AssignmentSubmissionRepository repo;

    // Save Submission
    public AssignmentSubmission save(
            AssignmentSubmission submission) {

        submission.setStatus("PENDING");

        if (submission.getMarks() == null) {
            submission.setMarks(0);
        }
        
        if(submission.getMarks() == null) {
            submission.setMarks(0);
        }

        return repo.save(submission);
    }
   // Get By ID
    public AssignmentSubmission getById(String id) {

        Optional<AssignmentSubmission> submission =
                repo.findById(id);

        return submission.orElse(null);
    }
    
    
    // Get All Submissions
    public List<AssignmentSubmission> getAll() {

        return repo.findAll();
    }
    
    public List<AssignmentSubmission> getByStudent(String email) {
        return repo.findByStudentEmail(email);
    }

    // Get By Assignment
    public List<AssignmentSubmission> getByAssignmentId(
            String assignmentId) {

        return repo.findByAssignmentId(
                assignmentId
        );
    }

    // Get By Student
    public List<AssignmentSubmission> getByStudentEmail(
            String email) {

        return repo.findByStudentEmail(
                email
        );
    }
}