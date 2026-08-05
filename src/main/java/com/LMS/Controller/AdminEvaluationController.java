package com.LMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LMS.Entity.AssignmentSubmission;
import com.LMS.Repository.AssignmentSubmissionRepository;

@Controller
public class AdminEvaluationController {

    @Autowired
    private AssignmentSubmissionRepository repo;

    @PostMapping("/admin/submission/evaluate")
    public String evaluateSubmission(

            @RequestParam String id,
            @RequestParam Integer marks

    ) {

        AssignmentSubmission submission =
                repo.findById(id).orElseThrow();

        submission.setMarks(marks);
        submission.setStatus("CHECKED");

        repo.save(submission);

        return "redirect:/admin/submissions";
    }
}