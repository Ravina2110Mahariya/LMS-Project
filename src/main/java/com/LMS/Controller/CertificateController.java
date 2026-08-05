package com.LMS.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LMS.Entity.Certificate;
import com.LMS.Service.CertificateService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    // =========================
    // DOWNLOAD CERTIFICATE PDF
    // =========================
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadCertificate(
            @RequestParam String courseId) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        byte[] pdf =
                certificateService.generateCertificate(
                        email,
                        courseId);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=LMS_Certificate.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // =========================
    // GENERATE CERTIFICATE
    // =========================
    @GetMapping("/student/generate-certificate/{courseId}")
    public String generateCertificate(
            @PathVariable String courseId,
            Principal principal) {

        certificateService.generateCertificate(
                principal.getName(),
                courseId);

        return "redirect:/student/certificates";
    }

    // =========================
    // MY CERTIFICATES PAGE
    // =========================
    @GetMapping("/student/certificates")
    public String certificates(
            org.springframework.ui.Model model,
            Principal principal) {

        model.addAttribute(
                "certificates",
                certificateService.myCertificates(
                        principal.getName()));

        return "student/certificates";
    }

    // =========================
    // CERTIFICATES API
    // =========================
    @GetMapping("/my-certificates")
    public ResponseEntity<List<Certificate>> myCertificates() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(
                certificateService.myCertificates(email));
    }
}