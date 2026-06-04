package com.LMS.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.Entity.VideoLecture;
import com.LMS.Service.VideoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService service;

    // UPLOAD VIDEO
    @PostMapping("/upload")
    public VideoLecture upload(

            @RequestParam String title,

            @RequestParam String courseId,

            @RequestParam String uploadedBy,

            @RequestParam MultipartFile file)

            throws IOException {

        return service.upload(
                title,
                courseId,
                uploadedBy,
                file
        );
    }

    // GET COURSE VIDEOS
    @GetMapping("/course/{courseId}")
    public List<VideoLecture> getVideos(
            @PathVariable String courseId) {

        return service.getByCourse(
                courseId
        );
    }
}