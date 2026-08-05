package com.LMS.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
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
    // GENERATE CERTIFICATE PDF
    // =========================
    public byte[] generateCertificate(String email, String courseId) {

        try {

            User user = userRepo.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            List<Certificate> certificates =
                    certificateRepo.findByStudentEmailAndCourseId(email, courseId);

            Certificate certificate =
                    certificates.isEmpty() ? null : certificates.get(0);

            if (certificate == null) {

                certificate = new Certificate();

                certificate.setUserId(user.getId());
                certificate.setStudentEmail(email);
                certificate.setCourseId(courseId);

                certificate.setCertificateNumber(
                        "CERT-" + UUID.randomUUID().toString().substring(0, 8));

                certificate.setIssueDate(LocalDateTime.now().toString());

                certificateRepo.save(certificate);
            }

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();

            document.addPage(page);

            PDPageContentStream content =
                    new PDPageContentStream(document, page);

            content.beginText();
            content.setFont(
                    new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 24);

            content.newLineAtOffset(170, 720);
            content.showText("LMS CERTIFICATE");
            content.endText();

            content.beginText();
            content.setFont(
                    new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);

            content.newLineAtOffset(100, 650);

            content.showText("This certifies that");

            content.newLineAtOffset(0, -40);
            content.showText("Student Name : " + user.getName());

            content.newLineAtOffset(0, -30);
            content.showText("Email : " + user.getEmail());

            content.newLineAtOffset(0, -30);
            content.showText("Course ID : " + courseId);

            content.newLineAtOffset(0, -30);
            content.showText("Certificate No : "
                    + certificate.getCertificateNumber());

            content.newLineAtOffset(0, -30);
            content.showText("Issue Date : "
                    + certificate.getIssueDate());

            content.newLineAtOffset(0, -30);
            content.showText("Status : COMPLETED");

            content.endText();
            content.close();

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            document.save(out);
            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Certificate generation failed", e);
        }
    }

    // =========================
    // STUDENT CERTIFICATES
    // =========================
    public List<Certificate> myCertificates(String email) {
        return certificateRepo.findByStudentEmail(email);
    }

    // =========================
    // GET BY ID
    // =========================
    public Certificate getCertificateById(String id) {

        return certificateRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Certificate not found"));
    }

    // =========================
    // ADMIN - GET ALL
    // =========================
    public List<Certificate> getAllCertificates() {
        return certificateRepo.findAll();
    }

    // =========================
    // ADMIN - SAVE
    // =========================
    public Certificate save(Certificate certificate) {
        return certificateRepo.save(certificate);
    }

    // =========================
    // ADMIN - DELETE
    // =========================
    public void delete(String id) {
        certificateRepo.deleteById(id);
    }
}