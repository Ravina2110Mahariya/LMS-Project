package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Notification;
import com.LMS.Service.NotificationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/notifications")
public class AdminNotificationPageController {

    private final NotificationService service;

    @GetMapping("")
    public String list(Model model) {

        model.addAttribute(
                "notification",
                new Notification());

        model.addAttribute(
                "notifications",
                service.getAll());   // अगर getAll() है

        return "admin/notification-list";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute(
                "notification",
                new Notification());

        return "admin/add-notification";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Notification notification) {

        notification.setCreatedBy("ADMIN");

        service.save(notification);

        return "redirect:/admin/notifications";
    }
    
 // =========================
 // EDIT NOTIFICATION
 // =========================
 @GetMapping("/edit/{id}")
 public String edit(
         @PathVariable String id,
         Model model) {

     model.addAttribute(
             "notification",
             service.getById(id));

     return "admin/add-notification";
 }

 // =========================
 // DELETE NOTIFICATION
 // =========================
 @GetMapping("/delete/{id}")
 public String delete(
         @PathVariable String id) {

     service.delete(id);

     return "redirect:/admin/notifications";
 }

}