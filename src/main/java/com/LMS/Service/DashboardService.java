package com.LMS.Service;

import org.springframework.stereotype.Service;

import com.LMS.Repository.AssignmentSubmissionRepository;
import com.LMS.Repository.CertificateRepository;
import com.LMS.Repository.EnrollmentRepository;
import com.LMS.Repository.NotificationRepository;
import com.LMS.dto.StudentDashboardDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final EnrollmentRepository enrollmentRepo;

    private final CertificateRepository certificateRepo;

    private final NotificationRepository notificationRepo;

    // ✅ USE AssignmentSubmissionRepository
    private final AssignmentSubmissionRepository submissionRepo;

    // =========================
    // STUDENT DASHBOARD
    // =========================
    public StudentDashboardDTO dashboard(
            String email) {

        StudentDashboardDTO dto =
                new StudentDashboardDTO();

        dto.setStudent(email);

        // =========================
        // ENROLLED COURSES
        // =========================
        dto.setEnrolledCourses(

                enrollmentRepo
                        .findByStudentEmail(email)
                        .size()
        );

        // =========================
        // CERTIFICATES
        // =========================
        dto.setCertificates(

                certificateRepo
                        .findByStudentEmail(email)
                        .size()
        );

        // =========================
        // NOTIFICATIONS
        // =========================
        dto.setNotifications(

                notificationRepo
                        .findByStudentEmail(email)
                        .size()
        );

        // =========================
        // SUBMISSIONS
        // =========================
        dto.setSubmissions(

                submissionRepo
                        .findByStudentEmail(email)
                        .size()
        );

        // =========================
        // COMPLETED COURSES
        // =========================
        dto.setCompletedCourses(

                certificateRepo
                        .findByStudentEmail(email)
                        .size()
        );

        return dto;
    }
}