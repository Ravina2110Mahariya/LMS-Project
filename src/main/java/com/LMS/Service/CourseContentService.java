package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.CourseContent;
import com.LMS.Entity.User;
import com.LMS.Repository.CourseContentRepository;
import com.LMS.Repository.EnrollmentRepository;
import com.LMS.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseContentService {

    private final CourseContentRepository contentRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final UserRepository userRepo;

    // ✅ ADD CONTENT (FINAL FIX)
    public CourseContent addContent(CourseContent c) {

        // safety check (optional but recommended)
        if (c.getFileName() == null) {
            throw new RuntimeException("File name is missing");
        }

        return contentRepo.save(c);
    }

    // ✅ SECURE CONTENT (ENROLLMENT CHECK)
    public List<CourseContent> getContent(String courseId, String email) {

        // 1. user find
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. enrollment check
        enrollmentRepo.findByUserIdAndCourseId(user.getId(), courseId)
                .orElseThrow(() -> new RuntimeException("You are not enrolled in this course"));

        // 3. return content
        return contentRepo.findByCourseId(courseId);
    }
}