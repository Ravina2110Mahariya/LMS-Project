package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Certificate;
import com.LMS.Service.CertificateService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/certificates")
@RequiredArgsConstructor
public class AdminCertificateController {

    private final CertificateService certificateService;

    // =========================
    // Certificate List
    // =========================
    @GetMapping
    public String list(Model model) {

        model.addAttribute(
                "certificates",
                certificateService.getAllCertificates());

        return "admin/certificate-list";
    }

    // =========================
    // Add Certificate Page
    // =========================
    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute(
                "certificate",
                new Certificate());

        return "admin/add-certificate";
    }

    // =========================
    // Save Certificate
    // =========================
    @PostMapping("/save")
    public String save(
            @ModelAttribute Certificate certificate) {

        certificateService.save(certificate);

        return "redirect:/admin/certificates";
    }

    // =========================
    // Edit Certificate
    // =========================
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "certificate",
                certificateService.getCertificateById(id));

        return "admin/add-certificate";
    }

    // =========================
    // Delete Certificate
    // =========================
    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id) {

        certificateService.delete(id);

        return "redirect:/admin/certificates";
    }
}