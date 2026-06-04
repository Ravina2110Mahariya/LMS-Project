package com.LMS.Controller;

import java.io.File;
import java.nio.file.*;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.Entity.CourseContent;
import com.LMS.Service.CourseContentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class CourseContentController {

    private final CourseContentService service;

    // =========================
    // ADD CONTENT
    // =========================
    @PostMapping
    public ResponseEntity<?> add(
            @RequestBody CourseContent c) {

        return ResponseEntity.ok(
                service.addContent(c)
        );
    }

    // =========================
    // UPLOAD FILE
    // =========================
    @PostMapping("/upload")
    public ResponseEntity<?> uploadContent(

            @RequestParam String courseId,
            @RequestParam String title,
            @RequestParam String type,
            @RequestParam MultipartFile file

    ) {

        try {

            // EMPTY FILE CHECK
            if (file.isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .body("File is empty");
            }

            // FOLDER SELECT
            String folder = "uploads/" +

                    ("PDF".equalsIgnoreCase(type)
                            ? "pdfs/"
                            : "videos/");

            // CREATE FOLDER
            File dir = new File(folder);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            // ORIGINAL FILE NAME
            String originalFileName =
                    file.getOriginalFilename();

            // REMOVE SPACES
            originalFileName =
                    originalFileName.replaceAll(" ", "_");

            // UNIQUE FILE NAME
            String fileName =
                    System.currentTimeMillis()
                            + "_"
                            + originalFileName;

            // SAVE PATH
            Path path =
                    Paths.get(folder, fileName);

            // SAVE FILE
            Files.write(path, file.getBytes());

            // SAVE DB
            CourseContent content =
                    new CourseContent();

            content.setCourseId(courseId);
            content.setTitle(title);
            content.setType(type);

            // SAVE FILE URL
            content.setFileUrl(fileName);

            // SAVE ORIGINAL NAME
            content.setFileName(originalFileName);

            return ResponseEntity.ok(
                    service.addContent(content)
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("Upload Failed: "
                            + e.getMessage());
        }
    }

    // =========================
    // GET COURSE CONTENT
    // =========================
    @GetMapping("/{courseId}")
    public ResponseEntity<?> getContent(
            @PathVariable String courseId) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        List<CourseContent> content =
                service.getContent(courseId, email);

        return ResponseEntity.ok(content);
    }

    // =========================
    // DOWNLOAD FILE
    // =========================
    @GetMapping("/download/{type}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(

            @PathVariable String type,
            @PathVariable String fileName

    ) {

        try {

            // DEBUG
            System.out.println("TYPE: " + type);
            System.out.println("FILE: " + fileName);

            // SELECT FOLDER
            String folder = "uploads/" +

                    ("pdf".equalsIgnoreCase(type)
                            ? "pdfs/"
                            : "videos/");

            // FILE PATH
            Path filePath =
                    Paths.get(folder)
                            .resolve(fileName)
                            .normalize();

            System.out.println(
                    "PATH: "
                            + filePath.toAbsolutePath()
            );

            // RESOURCE
            Resource resource =
                    new UrlResource(filePath.toUri());

            // FILE EXISTS CHECK
            if (!resource.exists()) {

                System.out.println("FILE NOT FOUND");

                return ResponseEntity
                        .notFound()
                        .build();
            }

            // CONTENT TYPE
            String contentType =
                    Files.probeContentType(filePath);

            if (contentType == null) {

                contentType =
                        "application/octet-stream";
            }

            // RETURN FILE
            return ResponseEntity.ok()

                    .contentType(
                            MediaType.parseMediaType(contentType)
                    )

                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\""
                                    + resource.getFilename()
                                    + "\""
                    )

                    .body(resource);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}