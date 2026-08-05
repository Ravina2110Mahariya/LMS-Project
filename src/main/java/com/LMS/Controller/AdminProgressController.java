package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Progress;
import com.LMS.Service.ProgressService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/progress")
@RequiredArgsConstructor
public class AdminProgressController {

    private final ProgressService progressService;

    @GetMapping("")
    public String list(Model model) {

        model.addAttribute(
                "progressList",
                progressService.getAll());

        return "admin/progress-list";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute(
                "progress",
                new Progress());

        return "admin/add-progress";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Progress progress) {

        progressService.save(progress);

        return "redirect:/admin/progress";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "progress",
                progressService.getById(id));

        return "admin/add-progress";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id) {

        progressService.delete(id);

        return "redirect:/admin/progress";
    }
}