package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.LMS.Entity.QuizResult;

@Repository
public interface QuizResultRepository
        extends MongoRepository<QuizResult, String> {

    List<QuizResult> findByStudentEmail(String email);
    
    long countByStudentEmail(String email);
}