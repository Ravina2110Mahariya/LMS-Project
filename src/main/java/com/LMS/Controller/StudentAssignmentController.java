package com.LMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.LMS.Entity.AssignmentSubmission;
import com.LMS.Service.AssignmentSubmissionService;

@Controller
public class StudentAssignmentController {

	@Autowired
	private AssignmentSubmissionService assignmentSubmissionService;
	
	
	@PostMapping("/student/assignment/submit")
	public String submitAssignment(
	        AssignmentSubmission submission) {

	    Authentication auth =
	            SecurityContextHolder.getContext()
	                                 .getAuthentication();

	    String email = auth.getName();

	    System.out.println("LOGIN EMAIL = " + email);
	    System.out.println("ASSIGNMENT ID = " + submission.getAssignmentId());
	    System.out.println("ANSWER = " + submission.getAnswer());
	    System.out.println("URL = " + submission.getSubmissionUrl());

	    submission.setStudentEmail(email);

	    assignmentSubmissionService.save(submission);

	    return "redirect:/student/my-submissions";
	}
}