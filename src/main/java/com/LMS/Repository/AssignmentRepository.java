package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Assignment;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {

    List<Assignment> findByCourseId(String courseId);

}