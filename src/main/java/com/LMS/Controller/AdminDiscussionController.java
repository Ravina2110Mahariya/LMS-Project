package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Discussion;
import com.LMS.Service.DiscussionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/discussions")
@RequiredArgsConstructor
public class AdminDiscussionController {

    private final DiscussionService discussionService;

    // List
    @GetMapping("")
    public String list(Model model) {

        model.addAttribute(
                "discussions",
                discussionService.getAll());

        return "admin/discussion-list";
    }

    // Add Page
    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute(
                "discussion",
                new Discussion());

        return "admin/add-discussion";
    }

    // Save
    @PostMapping("/save")
    public String save(
            @ModelAttribute Discussion discussion) {

        discussionService.save(discussion);

        return "redirect:/admin/discussions";
    }

    // Edit
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "discussion",
                discussionService.getById(id));

        return "admin/add-discussion";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id) {

        discussionService.delete(id);

        return "redirect:/admin/discussions";
    }
}