package com.LMS.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.Service.FileUploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService service;

    // ✅ Upload File
    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file) {

        String fileName = service.uploadFile(file);

        return ResponseEntity.ok(
                "File uploaded: " + fileName
        );
    }
}