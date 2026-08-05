package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Report;
import com.LMS.Service.ReportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/reports")
@RequiredArgsConstructor
public class AdminReportController {

    private final ReportService reportService;

    @GetMapping("")
    public String list(Model model) {

        model.addAttribute(
                "reports",
                reportService.getAll());

        return "admin/report-list";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute(
                "report",
                new Report());

        return "admin/add-report";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Report report) {

        reportService.save(report);

        return "redirect:/admin/reports";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "report",
                reportService.getById(id));

        return "admin/add-report";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id) {

        reportService.delete(id);

        return "redirect:/admin/reports";
    }
}