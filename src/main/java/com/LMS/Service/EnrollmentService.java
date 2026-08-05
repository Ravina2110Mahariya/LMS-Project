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
                        new RuntimeException("User not found"));

        repo.findByUserIdAndCourseId(
                user.getId(),
                e.getCourseId())
                .ifPresent(en -> {
                    throw new RuntimeException(
                            "Already enrolled in this course");
                });

        e.setUserId(user.getId());
        e.setStudentEmail(email);
        e.setStatus("ENROLLED");

        return repo.save(e);
    }

    // =========================
    // GET USER ENROLLMENTS
    // =========================
    public List<Enrollment> getUserEnrollments(
            String userId) {

        return repo.findByUserId(userId);
    }

        // =========================
        // MY COURSES BY EMAIL
        // =========================
    public List<EnrollmentDTO> getMyCoursesByEmail(String email) {

        System.out.println("EMAIL = " + email);

        List<Enrollment> enrollments =
                repo.findByStudentEmail(email);

        System.out.println("TOTAL ENROLLMENTS = "
                + enrollments.size());

        List<EnrollmentDTO> list = enrollments.stream()
                .map(e -> {

                    System.out.println(
                            "COURSE ID = "
                                    + e.getCourseId());

                    Course course =
                            courseRepo.findById(
                                    e.getCourseId())
                                    .orElse(null);

                    System.out.println(
                            "COURSE = "
                                    + course);

                    if (course == null) {
                        return null;
                    }

                    return new EnrollmentDTO(
                            course.getId(),
                            course.getTitle(),
                            course.getDescription(),
                            course.getCategory(),
                            e.getStatus()
                    );

                })
                .filter(dto -> dto != null)
                .toList();

        System.out.println(
                "DTO SIZE = "
                        + list.size());

        return list;
    }
    
 // =========================
 // GET ALL ENROLLMENTS
 // =========================
 public List<Enrollment> getAll() {

     return repo.findAll();
 }

 // =========================
 // GET BY ID
 // =========================
 public Enrollment getById(String id) {

     return repo.findById(id)
             .orElseThrow(() ->
                     new RuntimeException("Enrollment Not Found"));
 }

 // =========================
 // SAVE
 // =========================
 public Enrollment save(Enrollment enrollment) {

     return repo.save(enrollment);
 }

 // =========================
 // DELETE
 // =========================
 public void delete(String id) {

     repo.deleteById(id);
 }
}