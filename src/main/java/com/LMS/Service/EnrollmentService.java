package com.LMS.Service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.LMS.Entity.Course;
import com.LMS.Entity.Enrollment;
import com.LMS.Entity.User;
import com.LMS.Repository.CourseRepository;
import com.LMS.Repository.EnrollmentRepository;
import com.LMS.Repository.UserRepository;
import com.LMS.dto.EnrollmentDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository repo;

    private final CourseRepository courseRepo;

    private final UserRepository userRepo;

    // =========================
    // ENROLL COURSE
    // =========================
    public Enrollment enroll(Enrollment e) {

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = auth.getName();

        User user = userRepo.findByEmail(email)

                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        ));

        // =========================
        // SET USER ID
        // =========================
        e.setUserId(user.getId());

        // =========================
        // SET STUDENT EMAIL
        // =========================
        e.setStudentEmail(email);

        // =========================
        // ALREADY ENROLLED CHECK
        // =========================
        repo.findByUserIdAndCourseId(
                e.getUserId(),
                e.getCourseId()
        ).ifPresent(en -> {

            throw new RuntimeException(
                    "You are already enrolled in this course"
            );
        });

        // =========================
        // STATUS
        // =========================
        e.setStatus("ENROLLED");

        // =========================
        // SAVE
        // =========================
        return repo.save(e);
    }

    // =========================
    // USER ENROLLMENTS
    // =========================
    public List<Enrollment> getUserEnrollments(
            String userId) {

        return repo.findByUserId(userId);
    }

    // =========================
    // MY COURSES
    // =========================
    public List<EnrollmentDTO> getMyCourses(
            String userId) {

        List<Enrollment> enrollments =
                repo.findByUserId(userId);

        return enrollments.stream().map(e -> {

            Course c =
                    courseRepo.findById(
                            e.getCourseId()
                    )

                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Course not found"
                            ));

            return new EnrollmentDTO(

                    c.getTitle(),
                    c.getDescription(),
                    e.getStatus()

            );

        }).toList();
    }

    // =========================
    // JWT BASED
    // =========================
    public List<EnrollmentDTO> getMyCoursesByEmail(
            String email) {

        User user = userRepo.findByEmail(email)

                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        ));

        return getMyCourses(user.getId());
    }
}