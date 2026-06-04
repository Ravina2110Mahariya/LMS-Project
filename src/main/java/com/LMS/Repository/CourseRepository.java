package com.LMS.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Course;

public interface CourseRepository
        extends MongoRepository<Course, String> {

	// FIND COURSE BY TITLE
    Optional<Course> findByTitle(String title);
}