package com.LMS.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "certificates")
public class Certificate {

    @Id
    private String id;

    private String userId;

    // ✅ NEW FIELD
    private String studentEmail;

    private String courseId;

    private String certificateNumber;

    private LocalDateTime generatedAt;

    public Certificate() {
    }

    public Certificate(
            String id,
            String userId,
            String studentEmail,
            String courseId,
            String certificateNumber,
            LocalDateTime generatedAt) {

        this.id = id;
        this.userId = userId;
        this.studentEmail = studentEmail;
        this.courseId = courseId;
        this.certificateNumber = certificateNumber;
        this.generatedAt = generatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // =========================
    // STUDENT EMAIL
    // =========================
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(
            String studentEmail) {

        this.studentEmail = studentEmail;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(
            String courseId) {

        this.courseId = courseId;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(
            String certificateNumber) {

        this.certificateNumber = certificateNumber;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(
            LocalDateTime generatedAt) {

        this.generatedAt = generatedAt;
    }
}