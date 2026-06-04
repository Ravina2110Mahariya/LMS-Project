package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.LiveClass;

public interface LiveClassRepository
        extends MongoRepository<LiveClass, String> {

    List<LiveClass> findByCourseId(
            String courseId
    );
}