package com.LMS.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.Entity.VideoLecture;
import com.LMS.Repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository repo;

    private static final String UPLOAD_DIR =
            "uploads/videos/";

    // UPLOAD VIDEO
    public VideoLecture upload(
            String title,
            String courseId,
            String uploadedBy,
            MultipartFile file)
            throws IOException {

        // CREATE FOLDER
        File folder = new File(UPLOAD_DIR);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        // FILE NAME
        String fileName =
                System.currentTimeMillis()
                + "_"
                + file.getOriginalFilename();

        // FULL PATH
        Path path = Paths.get(
                UPLOAD_DIR + fileName
        );

        // SAVE FILE
        Files.copy(
                file.getInputStream(),
                path
        );

        // SAVE DB
        VideoLecture video =
                new VideoLecture();

        video.setTitle(title);
        video.setCourseId(courseId);
        video.setUploadedBy(uploadedBy);
        video.setVideoName(fileName);
        video.setVideoPath(path.toString());

        return repo.save(video);
    }

    // GET COURSE VIDEOS
    public List<VideoLecture>
    getByCourse(String courseId) {

        return repo.findByCourseId(courseId);
    }
}