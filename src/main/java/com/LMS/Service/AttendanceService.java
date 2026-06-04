package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Attendance;
import com.LMS.Repository.AttendanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository repo;

    // MARK ATTENDANCE
    public Attendance markAttendance(
            Attendance attendance) {

        return repo.save(attendance);
    }

    // STUDENT ATTENDANCE
    public List<Attendance> myAttendance(
            String email) {

        return repo.findByStudentEmail(email);
    }

    // COURSE ATTENDANCE
    public List<Attendance> courseAttendance(
            String courseId) {

        return repo.findByCourseId(courseId);
    }
}