package com.LMS.Service;


import org.springframework.stereotype.Service;

import com.LMS.Repository.AssignmentRepository;
import com.LMS.Repository.CertificateRepository;
import com.LMS.Repository.CourseRepository;
import com.LMS.Repository.EnrollmentRepository;
import com.LMS.Repository.QuizRepository;
import com.LMS.Repository.UserRepository;
import com.LMS.dto.AdminStatsDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepo;
    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final AssignmentRepository assignmentRepo;
    private final CertificateRepository certificateRepo;
    private final QuizRepository quizRepo;

    public AdminStatsDTO getStats() {

        AdminStatsDTO dto =
                new AdminStatsDTO();

        dto.setStudents(
                userRepo.count()
        );

        dto.setCourses(
                courseRepo.count()
        );

        dto.setEnrollments(
                enrollmentRepo.count()
        );

        dto.setAssignments(
                assignmentRepo.count()
        );

        dto.setCertificates(
                certificateRepo.count()
        );

        dto.setQuizzes(
                quizRepo.count()
        );

        return dto;
    }
}