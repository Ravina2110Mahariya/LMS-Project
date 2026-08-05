package com.LMS.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.Entity.Notes;
import com.LMS.Service.NotesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {

    private final NotesService service;

    // UPLOAD NOTES
    @PostMapping("/upload")
    public Notes upload(
            @RequestParam String title,
            @RequestParam String courseId,
            @RequestParam String uploadedBy,
            @RequestParam MultipartFile file
    ) throws IOException {

        return service.upload(
                title,
                courseId,
                uploadedBy,
                file
        );
    }

    // ALL NOTES
    @GetMapping("/all")
    public List<Notes> getAll() {

        return service.getAll();
    }

    // COURSE NOTES
    @GetMapping("/course/{courseId}")
    public List<Notes> getByCourse(
            @PathVariable String courseId) {

        return service.getByCourse(
                courseId
        );
    }
}