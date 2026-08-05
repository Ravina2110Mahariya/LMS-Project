package com.LMS.Controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.Entity.Announcement;
import com.LMS.Service.AnnouncementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/announcement")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService service;

    @PostMapping("/create")
    public String create(
            Announcement announcement) {

        String admin =
                SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        announcement.setCreatedBy(admin);

        service.save(announcement);

        return "redirect:/admin/announcements";
    }

    @GetMapping("/all")
    public List<Announcement> all() {

        return service.getAll();
    }
}