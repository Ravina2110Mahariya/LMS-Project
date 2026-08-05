package com.LMS.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.Entity.CourseContent;
import com.LMS.Service.CourseContentService;

import lombok.RequiredArgsConstructor;

@Controller
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
    // COURSE CONTENT courseId
    // =========================
    @GetMapping("/student/course-content/{courseId}")
    public String studentCourseContent(
            @PathVariable String courseId,
            org.springframework.ui.Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        List<CourseContent> contents =
                service.getContent(courseId, email);

        model.addAttribute("contents", contents);
        model.addAttribute("courseId", courseId);

        return "student/course-content";
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
    	System.out.println("========= UPLOAD HIT =========");

    	System.out.println("COURSE ID = " + courseId);
    	System.out.println("TITLE = " + title);
    	System.out.println("TYPE = " + type);
    	System.out.println("FILE = " + file.getOriginalFilename());

        try {

            if (file.isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .body("File is empty");
            }

            String folder = "uploads/" +

                    ("PDF".equalsIgnoreCase(type)
                            ? "pdfs/"
                            : "videos/");

            File dir = new File(folder);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originalFileName =
                    file.getOriginalFilename();

            originalFileName =
                    originalFileName.replaceAll(" ", "_");

            String fileName =
                    System.currentTimeMillis()
                            + "_"
                            + originalFileName;

            Path path =
                    Paths.get(folder, fileName);

            Files.write(path, file.getBytes());

            CourseContent content =
                    new CourseContent();

            content.setCourseId(courseId);
            content.setTitle(title);
            content.setType(type);

            // FINAL FILE URL
            content.setFileUrl(
                    "/content/download/"
                            + type.toLowerCase()
                            + "/"
                            + fileName
            );

            content.setFileName(originalFileName);

            return ResponseEntity.ok(
                    service.addContent(content)
            );

        } catch (Exception e) {

            e.printStackTrace();

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

        System.out.println("===== DOWNLOAD HIT =====");
        System.out.println("TYPE = " + type);
        System.out.println("FILE = " + fileName);

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        System.out.println("USER = " + auth.getName());
        System.out.println("ROLES = " + auth.getAuthorities());

        try {

            String folder = "uploads/" +
                    ("pdf".equalsIgnoreCase(type)
                            ? "pdfs/"
                            : "videos/");

            Path filePath =
                    Paths.get(folder)
                            .resolve(fileName)
                            .normalize();

            Resource resource =
                    new UrlResource(filePath.toUri());

            if (!resource.exists()) {

                return ResponseEntity
                        .notFound()
                        .build();
            }

            String contentType =
                    Files.probeContentType(filePath);

            if (contentType == null) {

                contentType =
                        "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(
                            MediaType.parseMediaType(contentType)
                    )
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" +
                            resource.getFilename() + "\""
                    )
                    .body(resource);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
    // =========================
    // DELETE CONTENT
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContent(
            @PathVariable String id) {

        service.deleteContent(id);

        return ResponseEntity.ok(
                "Content Deleted Successfully"
        );
    }

 // =========================
    // TEST COURSE CONTENT
    // =========================
    @GetMapping("/test/{courseId}")
    public List<CourseContent> test(
            @PathVariable String courseId) {

        return service.getByCourseId(courseId);
    }

    }

