package com.LMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LMS.Entity.AssignmentSubmission;
import com.LMS.Service.AssignmentSubmissionService;

@Controller
public class InstructorPageController {

    @Autowired
    private AssignmentSubmissionService assignmentSubmissionService;

    // View All Submissions
    @GetMapping("/instructor/submissions")
    public String viewSubmissions(Model model) {

        model.addAttribute(
                "submissions",
                assignmentSubmissionService.getAll()
        );

        return "instructor/submissions";
    }

    // Open Evaluation Page
    @GetMapping("/instructor/evaluate/{id}")
    public String evaluatePage(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "submission",
                assignmentSubmissionService.getById(id)
        );

        return "instructor/evaluate-submission";
    }

    // Save Evaluation
    @PostMapping("/instructor/evaluate")
    public String evaluateSubmission(

            @RequestParam String id,
            @RequestParam Integer marks,
            @RequestParam String feedback,
            @RequestParam String status) {

        AssignmentSubmission submission =
                assignmentSubmissionService.getById(id);

        submission.setMarks(marks);
        submission.setFeedback(feedback);
        submission.setStatus(status);

        assignmentSubmissionService.save(submission);

        return "redirect:/instructor/submissions";
    }
}