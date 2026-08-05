package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.CourseContent;

public interface CourseContentRepository
        extends MongoRepository<CourseContent, String> {

    List<CourseContent> findByCourseId
            (String courseId
    );
}