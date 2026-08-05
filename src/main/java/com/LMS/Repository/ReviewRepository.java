package com.LMS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByCourseId(String courseId);

    List<Review> findByStudentEmail(String studentEmail);

    Optional<Review> findByCourseIdAndStudentEmail(
            String courseId,
            String studentEmail
    );
}