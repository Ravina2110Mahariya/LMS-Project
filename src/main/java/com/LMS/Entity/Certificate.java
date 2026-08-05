package com.LMS.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "certificates")
public class Certificate {

    @Id
    private String id;

    private String userId;

    private String studentEmail;

    private String courseId;

    private String certificateNumber;

    private String issueDate;

    public Certificate() {
    }

    public Certificate(
            String id,
            String userId,
            String studentEmail,
            String courseId,
            String certificateNumber,
            String issueDate) {

        this.id = id;
        this.userId = userId;
        this.studentEmail = studentEmail;
        this.courseId = courseId;
        this.certificateNumber = certificateNumber;
        this.issueDate = issueDate;
    }

    // =========================
    // ID
    // =========================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // =========================
    // USER ID
    // =========================

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

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    // =========================
    // COURSE ID
    // =========================

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    // =========================
    // CERTIFICATE NUMBER
    // =========================

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    // =========================
    // ISSUE DATE
    // =========================

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
}

