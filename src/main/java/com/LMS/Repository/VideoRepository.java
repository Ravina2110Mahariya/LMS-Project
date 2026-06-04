package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.VideoLecture;

public interface VideoRepository
        extends MongoRepository<VideoLecture, String> {

    List<VideoLecture>
    findByCourseId(String courseId);
}