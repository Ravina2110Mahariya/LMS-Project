package com.LMS.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.LiveClass;
import com.LMS.Service.LiveClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/live")
@RequiredArgsConstructor
public class LiveClassController {

    private final LiveClassService service;

    // ADMIN CREATE LIVE CLASS
    @PostMapping("/create")
    public LiveClass create(
            @RequestBody LiveClass liveClass) {

        return service.create(
                liveClass
        );
    }

    // GET ALL LIVE CLASSES
    @GetMapping("/all")
    public List<LiveClass> getAll() {

        return service.getAll();
    }

    // GET COURSE LIVE CLASSES
    @GetMapping("/course/{courseId}")
    public List<LiveClass> getByCourse(
            @PathVariable String courseId) {

        return service.getByCourse(
                courseId
        );
    }
}