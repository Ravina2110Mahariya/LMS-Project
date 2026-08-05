package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.LMS.Entity.Announcement;
import com.LMS.Service.AnnouncementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminAnnouncementPageController {

    private final AnnouncementService service;

    @GetMapping("/admin/announcements")
    public String announcements(Model model) {

        model.addAttribute("announcement",
                new Announcement());

        model.addAttribute("announcements",
                service.getAll());

        return "admin/announcements";
    }

    @PostMapping("/admin/announcements/save")
    public String save(
            @ModelAttribute Announcement announcement) {

        announcement.setCreatedBy("ADMIN");

        service.save(announcement);

        return "redirect:/admin/announcements";
    }

    @GetMapping("/admin/announcements/delete/{id}")
    public String delete(@PathVariable String id) {

        service.delete(id);

        return "redirect:/admin/announcements";
    }
}