package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Assignment;
import com.LMS.Repository.AssignmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    // Save Assignment
    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    // Get All Assignments
    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    // Student Assignments
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    // Course Wise Assignment
    public List<Assignment> getByCourse(String courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }

    // Get Assignment By Id
    public Assignment getById(String id) {
        return assignmentRepository.findById(id).orElse(null);
    }

    // Delete Assignment
    public void delete(String id) {
        assignmentRepository.deleteById(id);
    }

}