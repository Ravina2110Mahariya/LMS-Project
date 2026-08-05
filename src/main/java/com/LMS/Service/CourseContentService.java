package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.CourseContent;
import com.LMS.Repository.CourseContentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseContentService {

    private final CourseContentRepository repository;

    // =========================
    // ADMIN
    // =========================

    public List<CourseContent> getAll() {
        return repository.findAll();
    }

    public CourseContent save(CourseContent content) {
        return repository.save(content);
    }

    public CourseContent getById(String id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Course Content Not Found"));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    // =========================
    // STUDENT
    // =========================

    public CourseContent addContent(CourseContent content) {
        return repository.save(content);
    }

    public List<CourseContent> getContent(String courseId, String email) {
        // Future me enrollment check kar sakte ho
        return repository.findByCourseId(courseId);
    }

    public void deleteContent(String id) {
        repository.deleteById(id);
    }

    public List<CourseContent> getByCourseId(String courseId) {
        return repository.findByCourseId(courseId);
    }
}