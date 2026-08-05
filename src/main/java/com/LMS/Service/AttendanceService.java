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

    // =========================
    // SAVE / MARK ATTENDANCE
    // =========================
    public Attendance save(Attendance attendance) {
        return repo.save(attendance);
    }

    public Attendance markAttendance(Attendance attendance) {
        return repo.save(attendance);
    }

    // =========================
    // GET ALL
    // =========================
    public List<Attendance> getAll() {
        return repo.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    public Attendance getById(String id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Attendance Not Found"));
    }

    // =========================
    // DELETE
    // =========================
    public void delete(String id) {
        repo.deleteById(id);
    }

    // =========================
    // STUDENT ATTENDANCE
    // =========================
    public List<Attendance> myAttendance(String email) {
        return repo.findByStudentEmail(email);
    }

    // =========================
    // COURSE ATTENDANCE
    // =========================
    public List<Attendance> courseAttendance(String courseId) {
        return repo.findByCourseId(courseId);
    }

}