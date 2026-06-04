package com.LMS.Controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Certificate;
import com.LMS.Service.CertificateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService service;

    // ✅ DOWNLOAD CERTIFICATE
    @GetMapping("/download")
    public ResponseEntity<?> downloadCertificate(
            @RequestParam String courseId) {

        try {

            // ✅ DEBUG
            System.out.println(
                    "USER: " +
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
            );

            // ✅ EMAIL FROM JWT
            String email = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();

            // ✅ GENERATE CERTIFICATE
            byte[] pdf =
                    service.generateCertificate(
                            email,
                            courseId
                    );

            // ✅ RETURN FILE
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=certificate.txt"
                    )
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(pdf);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // ✅ MY CERTIFICATES
    @GetMapping("/my-certificates")
    public List<Certificate> myCertificates() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return service.myCertificates(email);
    }

    // ✅ TEST API
    @GetMapping("/test")
    public String test() {

        return "Certificate API Working";
    }
}