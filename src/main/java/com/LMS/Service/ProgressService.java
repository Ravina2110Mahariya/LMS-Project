package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Progress;
import com.LMS.Entity.User;
import com.LMS.Repository.ProgressRepository;
import com.LMS.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepo;
    private final UserRepository userRepo;

    // ✅ ADD PROGRESS
    public Progress addProgress(Progress progress) {

        return progressRepo.save(progress);
    }

    // ✅ GET MY PROGRESS
    public List<Progress> getMyProgress(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return progressRepo.findByUserId(user.getId());
    }

    // ✅ UPDATE PROGRESS BY ID
    public Progress updateProgress(String id,
                                   Progress updatedProgress) {

        Progress existing = progressRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Progress not found"));

        existing.setCompletedLessons(
                updatedProgress.getCompletedLessons()
        );

        existing.setTotalLessons(
                updatedProgress.getTotalLessons()
        );

        // ✅ CALCULATE %
        double percent =
                (existing.getCompletedLessons() * 100.0)
                        / existing.getTotalLessons();

        existing.setPercentage(percent);

        return progressRepo.save(existing);
    }

    // ✅ AUTO UPDATE USING JWT
    public Progress updateProgress(String email,
                                   String courseId) {

        // ✅ USER FIND
        User user = userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // ✅ EXISTING PROGRESS CHECK
        Progress progress = progressRepo
                .findByUserIdAndCourseId(
                        user.getId(),
                        courseId
                )
                .orElse(new Progress());

        // ✅ SET DATA
        progress.setUserId(user.getId());
        progress.setCourseId(courseId);

        // ✅ UPDATE LESSON COUNT
        progress.setCompletedLessons(
                progress.getCompletedLessons() + 1
        );

        // ✅ DEFAULT TOTAL LESSONS
        if (progress.getTotalLessons() == 0) {
            progress.setTotalLessons(10);
        }

        // ✅ CALCULATE %
        double percent =
                (progress.getCompletedLessons() * 100.0)
                        / progress.getTotalLessons();

        progress.setPercentage(percent);

        // ✅ SAVE
        return progressRepo.save(progress);
    }
}