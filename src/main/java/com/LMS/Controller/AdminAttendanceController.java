package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Attendance;
import com.LMS.Service.AttendanceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/attendance")
@RequiredArgsConstructor
public class AdminAttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping("")
    public String list(Model model) {

        model.addAttribute(
                "attendanceList",
                attendanceService.getAll());

        return "admin/attendance-list";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute(
                "attendance",
                new Attendance());

        return "admin/add-attendance";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Attendance attendance) {

        attendanceService.save(attendance);

        return "redirect:/admin/attendance";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "attendance",
                attendanceService.getById(id));

        return "admin/add-attendance";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id) {

        attendanceService.delete(id);

        return "redirect:/admin/attendance";
    }

}