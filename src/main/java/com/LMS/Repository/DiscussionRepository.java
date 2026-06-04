package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Discussion;

public interface DiscussionRepository
        extends MongoRepository<Discussion, String> {

    List<Discussion> findByCourseId(String courseId);

    List<Discussion> findByStudentEmail(String studentEmail);
}