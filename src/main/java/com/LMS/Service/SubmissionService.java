package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.AssignmentSubmission;
import com.LMS.Repository.AssignmentSubmissionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final AssignmentSubmissionRepository repo;

    // =========================
    // SUBMIT ASSIGNMENT
    // =========================
    public AssignmentSubmission submit(
            AssignmentSubmission submission) {

        // =========================
        // STATUS
        // =========================
        submission.setStatus(
                "SUBMITTED"
        );

        // =========================
        // SAVE
        // =========================
        return repo.save(submission);
    }

    // =========================
    // GET BY ASSIGNMENT
    // =========================
    public List<AssignmentSubmission>
    getByAssignment(

            String assignmentId

    ) {

        return repo.findByAssignmentId(
                assignmentId
        );
    }

    // =========================
    // GET STUDENT SUBMISSIONS
    // =========================
    public List<AssignmentSubmission>
    getByStudent(

            String email

    ) {

        return repo.findByStudentEmail(
                email
        );
    }

    // =========================
    // GIVE MARKS
    // =========================
    public AssignmentSubmission giveMarks(

            String submissionId,
            Integer marks

    ) {

        AssignmentSubmission submission =

                repo.findById(submissionId)

                .orElseThrow(() ->

                        new RuntimeException(
                                "Submission not found"
                        )
                );

        // =========================
        // SET MARKS
        // =========================
        submission.setMarks(
                marks
        );

        // =========================
        // STATUS UPDATE
        // =========================
        submission.setStatus(
                "CHECKED"
        );

        // =========================
        // SAVE
        // =========================
        return repo.save(submission);
    }
}