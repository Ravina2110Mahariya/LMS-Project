package com.LMS.Service;

import org.springframework.stereotype.Service;

import com.LMS.Repository.AssignmentRepository;
import com.LMS.Repository.AssignmentSubmissionRepository;
import com.LMS.Repository.CertificateRepository;
import com.LMS.Repository.CourseRepository;
import com.LMS.Repository.EnrollmentRepository;
import com.LMS.Repository.LiveClassRepository;
import com.LMS.Repository.NotificationRepository;
import com.LMS.dto.StudentDashboardDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CourseRepository courseRepo;

    private final AssignmentSubmissionRepository submissionRepo;

    private final EnrollmentRepository enrollmentRepo;
    
    private final AssignmentRepository assignmentRepository;

    private final CertificateRepository certificateRepo;

    private final LiveClassRepository liveClassRepo;

    private final NotificationRepository notificationRepo;

    // =========================
    // STUDENT DASHBOARD
    // =========================
    public StudentDashboardDTO dashboard(String email) {

        StudentDashboardDTO dto =
                new StudentDashboardDTO();

        dto.setStudent(email);

        // TOTAL COURSES
        dto.setTotalCourses(
                courseRepo.count()
        );

        // ENROLLED COURSES
        dto.setEnrolledCourses(
                enrollmentRepo
                        .findByStudentEmail(email)
                        .size()
        );
        
     // ASSIGNMENTS COURSES
        dto.setTotalAssignments(
                assignmentRepository.count()
        );

        // CERTIFICAT
        dto.setTotalCertificates(
                certificateRepo
                        .findByStudentEmail(email)
                        .size()
        );

        // NOTIFICATIONS
        dto.setNotifications(
                notificationRepo
                        .findByStudentEmail(email)
                        .size()
        );

        // ASSIGNMENT SUBMISSIONS
        dto.setSubmissions(
                submissionRepo
                        .findByStudentEmail(email)
                        .size()
        );

        // COMPLETED COURSES
        dto.setCompletedCourses(
                certificateRepo
                        .findByStudentEmail(email)
                        .size()
        );

        // LIVE CLASSES
        dto.setLiveClasses(
                liveClassRepo.count()
        );

        return dto;
    }
}