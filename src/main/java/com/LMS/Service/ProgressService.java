package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Progress;
import com.LMS.Entity.User;
import com.LMS.Repository.CertificateRepository;
import com.LMS.Repository.ProgressRepository;
import com.LMS.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepo;
    private final UserRepository userRepo;

    private final CertificateRepository certificateRepo;
    private final CertificateService certificateService;

    // ADD PROGRESS
    public Progress addProgress(Progress progress) {
        return progressRepo.save(progress);
    }

    // GET MY PROGRESS
    public List<Progress> getMyProgress(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return progressRepo.findByUserId(user.getId());
    }

    // UPDATE PROGRESS BY ID
    public Progress updateProgress(
            String id,
            Progress updatedProgress) {

        Progress existing =
                progressRepo.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Progress not found"));

        existing.setCompletedLessons(
                updatedProgress.getCompletedLessons());

        existing.setTotalLessons(
                updatedProgress.getTotalLessons());

        double percent =
                (existing.getCompletedLessons() * 100.0)
                        / existing.getTotalLessons();

        existing.setPercentage(percent);

        return progressRepo.save(existing);
    }

    // AUTO UPDATE USING JWT
    public Progress updateProgress(
            String email,
            String courseId) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Progress progress =
                progressRepo.findByUserIdAndCourseId(
                        user.getId(),
                        courseId)
                        .orElse(new Progress());

        progress.setUserId(user.getId());
        progress.setCourseId(courseId);

        progress.setCompletedLessons(
                progress.getCompletedLessons() + 1);

        if (progress.getTotalLessons() == 0) {
            progress.setTotalLessons(10);
        }

        double percent =
                (progress.getCompletedLessons() * 100.0)
                        / progress.getTotalLessons();

        progress.setPercentage(percent);

        if (progress.getPercentage() >= 100) {

            boolean alreadyExists =
                    certificateRepo
                            .findByStudentEmail(email)
                            .stream()
                            .anyMatch(c ->
                                    c.getCourseId()
                                            .equals(courseId));

            if (!alreadyExists) {

                certificateService.generateCertificate(
                        email,
                        courseId);
            }
        }

        return progressRepo.save(progress);
    }
 // =========================
 // GET ALL
 // =========================
 public List<Progress> getAll() {

     return progressRepo.findAll();
 }

 // =========================
 // GET BY ID
 // =========================
 public Progress getById(String id) {

     return progressRepo.findById(id)
             .orElseThrow(() ->
                     new RuntimeException("Progress Not Found"));
 }

 // =========================
 // SAVE
 // =========================
 public Progress save(Progress progress) {

     return progressRepo.save(progress);
 }

 // =========================
 // DELETE
 // =========================
 public void delete(String id) {

     progressRepo.deleteById(id);
 }
}