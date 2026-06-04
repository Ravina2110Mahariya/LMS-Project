package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Assignment;
import com.LMS.Repository.AssignmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository repo;

    // ✅ Add Assignment
    public Assignment addAssignment(Assignment assignment) {
        return repo.save(assignment);
    }

    // ✅ Get Assignments by Course
    public List<Assignment> getByCourse(String courseId) {
        return repo.findByCourseId(courseId);
    }
}