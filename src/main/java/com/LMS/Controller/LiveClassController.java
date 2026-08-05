package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.LiveClass;
import com.LMS.Service.LiveClassService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/liveclasses")
@RequiredArgsConstructor
public class LiveClassController {

    private final LiveClassService liveClassService;

    // List
    @GetMapping("")
    public String list(Model model) {

        model.addAttribute(
                "liveClasses",
                liveClassService.getAll());

        return "admin/liveclass-list";
    }

    // Add Page
    @GetMapping("/add")
    public String addPage(Model model) {

        model.addAttribute(
                "liveClass",
                new LiveClass());

        return "admin/add-liveclass";
    }

    // Save
    @PostMapping("/save")
    public String save(
            @ModelAttribute LiveClass liveClass) {

        liveClassService.save(liveClass);

        return "redirect:/admin/liveclasses";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id) {

        liveClassService.delete(id);

        return "redirect:/admin/liveclasses";
    }

    // Edit
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "liveClass",
                liveClassService.getById(id));

        return "admin/add-liveclass";
    }

}