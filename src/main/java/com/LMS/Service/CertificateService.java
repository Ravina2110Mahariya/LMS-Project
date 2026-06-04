package com.LMS.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Certificate;
import com.LMS.Entity.User;
import com.LMS.Repository.CertificateRepository;
import com.LMS.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepo;

    private final UserRepository userRepo;

    // =========================
    // GENERATE CERTIFICATE
    // =========================
    public byte[] generateCertificate(

            String email,
            String courseId

    ) {

        // =========================
        // FIND USER
        // =========================
        User user = userRepo.findByEmail(email)

                .orElseThrow(() ->

                        new RuntimeException(
                                "User not found"
                        )
                );

        // =========================
        // CREATE CERTIFICATE
        // =========================
        Certificate certificate =
                new Certificate();

        // =========================
        // SET USER ID
        // =========================
        certificate.setUserId(
                user.getId()
        );

        // =========================
        // SET STUDENT EMAIL
        // =========================
        certificate.setStudentEmail(
                email
        );

        // =========================
        // SET COURSE
        // =========================
        certificate.setCourseId(
                courseId
        );

        // =========================
        // CERTIFICATE NUMBER
        // =========================
        certificate.setCertificateNumber(

                "CERT-"

                        + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
        );

        // =========================
        // DATE TIME
        // =========================
        certificate.setGeneratedAt(
                LocalDateTime.now()
        );

        // =========================
        // SAVE
        // =========================
        certificateRepo.save(certificate);

        // =========================
        // CONTENT
        // =========================
        String content =

                "===== LMS CERTIFICATE =====\n\n"

                + "Student: "
                + user.getName()
                + "\n"

                + "Email: "
                + user.getEmail()
                + "\n"

                + "Course ID: "
                + courseId
                + "\n"

                + "Certificate No: "
                + certificate.getCertificateNumber()
                + "\n\n"

                + "Status: COMPLETED\n\n"

                + "Congratulations!\n";

        // =========================
        // RETURN FILE
        // =========================
        return content.getBytes(
                StandardCharsets.UTF_8
        );
    }

    // =========================
    // MY CERTIFICATES
    // =========================
    public List<Certificate> myCertificates(
            String email) {

        return certificateRepo
                .findByStudentEmail(email);
    }
}