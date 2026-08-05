package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.VideoLecture;
import com.LMS.Service.VideoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/videos")
@RequiredArgsConstructor
public class AdminVideoController {

    private final VideoService videoService;

    // Video List
    @GetMapping("")
    public String videoList(Model model) {

        model.addAttribute(
                "videos",
                videoService.getAll());

        return "admin/video-list";
    }

    // Add Video Page
    @GetMapping("/add")
    public String addVideo(Model model) {

        model.addAttribute(
                "video",
                new VideoLecture());

        return "admin/add-video";
    }

    // Save Video
    @PostMapping("/save")
    public String saveVideo(
            @ModelAttribute VideoLecture video) {

        videoService.save(video);

        return "redirect:/admin/videos";
    }

    // Edit Video
    @GetMapping("/edit/{id}")
    public String editVideo(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "video",
                videoService.getById(id));

        return "admin/add-video";
    }

    // Delete Video
    @GetMapping("/delete/{id}")
    public String deleteVideo(
            @PathVariable String id) {

        videoService.delete(id);

        return "redirect:/admin/videos";
    }
}