package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Assignment;
import com.LMS.Service.AssignmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    // Assignment List
    @GetMapping("")
    public String assignmentList(Model model) {

        model.addAttribute("assignments",
                assignmentService.getAllAssignments());

        return "admin/assignment-list";
    }

    // Add Assignment Page
    @GetMapping("/add")
    public String addAssignmentPage(Model model) {

        model.addAttribute("assignment",
                new Assignment());

        return "admin/add-assignment";
    }

    // Save Assignment
    @PostMapping("/save")
    public String saveAssignment(
            @ModelAttribute Assignment assignment) {

        assignmentService.save(assignment);

        return "redirect:/admin/assignments";
    }

    // Delete Assignment
    @GetMapping("/delete/{id}")
    public String deleteAssignment(
            @PathVariable String id) {

        assignmentService.delete(id);

        return "redirect:/admin/assignments";
    }
}