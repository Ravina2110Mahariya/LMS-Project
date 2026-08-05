package com.LMS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Enrollment;

public interface EnrollmentRepository
        extends MongoRepository<Enrollment, String> {

    List<Enrollment> findByUserId(
            String userId
    );

    Optional<Enrollment>
    findByUserIdAndCourseId(

            String userId,
            String courseId
    );


    List<Enrollment> findByStudentEmail(
            String studentEmail
    );
}