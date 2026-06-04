package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Notes;

public interface NotesRepository
        extends MongoRepository<Notes, String> {

    List<Notes> findByCourseId(
            String courseId
    );
}