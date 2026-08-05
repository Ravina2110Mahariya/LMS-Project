package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.VideoLecture;
import com.LMS.Repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository repository;

    public VideoLecture save(VideoLecture video) {

        return repository.save(video);
    }

    public List<VideoLecture> getAll() {

        return repository.findAll();
    }

    public List<VideoLecture> getByCourse(String courseId) {

        return repository.findByCourseId(courseId);
    }
    
    public VideoLecture getById(String id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Video Not Found"));
    }

    public void delete(String id) {

        repository.deleteById(id);
    }

}