package com.LMS.Controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Attendance;
import com.LMS.Service.AttendanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService service;

    // ADMIN MARK ATTENDANCE
    @PostMapping("/mark")
    public Attendance markAttendance(
            @RequestBody Attendance attendance) {

        return service.markAttendance(
                attendance
        );
    }

    // STUDENT MY ATTENDANCE
    @GetMapping("/my")
    public List<Attendance> myAttendance(
            Authentication auth) {

        return service.myAttendance(
                auth.getName()
        );
    }

    // ADMIN COURSE ATTENDANCE
    @GetMapping("/course/{courseId}")
    public List<Attendance> courseAttendance(
            @PathVariable String courseId) {

        return service.courseAttendance(
                courseId
        );
    }
}