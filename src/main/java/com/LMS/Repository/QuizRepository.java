package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Quiz;

public interface QuizRepository 
        extends MongoRepository<Quiz, String> {

    List<Quiz> findByCourseId(String courseId);
}