package com.LMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.VideoLecture;
import com.LMS.Service.VideoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    // =========================
    // ADD VIDEO
    // =========================
    @PostMapping("/add")
    public ResponseEntity<VideoLecture> addVideo(
            @RequestBody VideoLecture video) {

        return ResponseEntity.ok(
                videoService.save(video));
    }

    // =========================
    // GET ALL VIDEOS
    // =========================
    @GetMapping("/all")
    public ResponseEntity<List<VideoLecture>> getAllVideos() {

        return ResponseEntity.ok(
                videoService.getAll());
    }

    // =========================
    // GET VIDEOS BY COURSE
    // =========================
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<VideoLecture>> getVideosByCourse(
            @PathVariable String courseId) {

        return ResponseEntity.ok(
                videoService.getByCourse(courseId));
    }

    // =========================
    // GET VIDEO BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<VideoLecture> getVideoById(
            @PathVariable String id) {

        return ResponseEntity.ok(
                videoService.getById(id));
    }

    // =========================
    // DELETE VIDEO
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(
            @PathVariable String id) {

        videoService.delete(id);

        return ResponseEntity.ok("Video Deleted Successfully");
    }

}