package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.LiveClass;
import com.LMS.Repository.LiveClassRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LiveClassService {

    private final LiveClassRepository repo;

    // CREATE LIVE CLASS
    public LiveClass create(
            LiveClass liveClass) {

        return repo.save(liveClass);
    }

    // ALL LIVE CLASSES
    public List<LiveClass> getAll() {

        return repo.findAll();
    }

    // COURSE LIVE CLASSES
    public List<LiveClass> getByCourse(
            String courseId) {

        return repo.findByCourseId(
                courseId
        );
    }
}