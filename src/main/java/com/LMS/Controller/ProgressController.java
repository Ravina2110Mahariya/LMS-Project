package com.LMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.Entity.Progress;
import com.LMS.Service.ProgressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService service;

    //  ADD PROGRESS
    @PostMapping
    public Progress addProgress(@RequestBody Progress progress) {

        return service.addProgress(progress);
    }

    //  GET MY PROGRESS
    @GetMapping("/my-progress")
    public List<Progress> myProgress() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return service.getMyProgress(email);
    }

    //  UPDATE PROGRESS BY ID
    @PutMapping("/{id}")
    public Progress updateProgress(
            @PathVariable String id,
            @RequestBody Progress progress) {

        return service.updateProgress(id, progress);
    }

    //  AUTO UPDATE USING JWT
    @PostMapping("/update")
    public ResponseEntity<?> update(
            @RequestParam String courseId) {

        try {

            //  DEBUG
            System.out.println(
                    "USER: " +
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
            );

            //  EMAIL FROM JWT
            String email = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();

            Progress progress =
                    service.updateProgress(email, courseId);

            return ResponseEntity.ok(progress);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}