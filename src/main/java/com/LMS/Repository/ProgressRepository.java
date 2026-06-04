package com.LMS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Progress;

public interface ProgressRepository
        extends MongoRepository<Progress, String> {

    // ✅ FIND BY USER ID
    List<Progress> findByUserId(String userId);

    // ✅ FIND BY USER ID + COURSE ID
    Optional<Progress> findByUserIdAndCourseId(
            String userId,
            String courseId
    );
}