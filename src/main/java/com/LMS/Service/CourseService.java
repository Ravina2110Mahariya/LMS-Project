package com.LMS.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.LMS.Entity.Course;
import com.LMS.Exception.CourseNotFoundException;
import com.LMS.Exception.ResourceAlreadyExistsException;
import com.LMS.Repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repo;

    // =========================
    // ADD COURSE
    // =========================
    public Course addCourse(Course course) {

        repo.findByTitle(course.getTitle())
                .ifPresent(c -> {
                    throw new ResourceAlreadyExistsException(
                            "Course already exists with title : "
                                    + course.getTitle()
                    );
                });

        return repo.save(course);
    }

    // =========================
    // GET ALL COURSES
    // =========================
    public List<Course> getAllCourses() {

        return repo.findAll();
    }

    // =========================
    // PAGINATION + SORTING
    // =========================
    public Page<Course> getCourses(
            int page,
            int size,
            String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy)
        );

        return repo.findAll(pageable);
    }

    // =========================
    // GET COURSE BY ID
    // =========================
    public Course getCourseById(String id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                "Course not found with id : " + id
                        ));
    }

    // =========================
    // UPDATE COURSE
    // =========================
    public Course updateCourse(
            String id,
            Course updatedCourse) {

        Course existing = getCourseById(id);

        existing.setTitle(updatedCourse.getTitle());
        existing.setDescription(updatedCourse.getDescription());
        existing.setInstructor(updatedCourse.getInstructor());
        existing.setPrice(updatedCourse.getPrice());

        return repo.save(existing);
    }

    // =========================
    // DELETE COURSE
    // =========================
    public String deleteCourse(String id) {

        Course existing = getCourseById(id);

        repo.delete(existing);

        return "Course deleted successfully";
    }
} 