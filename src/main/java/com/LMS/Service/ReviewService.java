package com.LMS.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Review;
import com.LMS.Repository.ReviewRepository;
import com.LMS.dto.ReviewResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // =========================
    // ADD / UPDATE REVIEW
    // =========================
    public Review addReview(Review review) {

        Optional<Review> existingReview =
                reviewRepository.findByCourseIdAndStudentEmail(
                        review.getCourseId(),
                        review.getStudentEmail()
                );

        // 🔥 If already exists → UPDATE
        if (existingReview.isPresent()) {

            Review oldReview = existingReview.get();

            oldReview.setRating(review.getRating());
            oldReview.setComment(review.getComment());
            oldReview.setCreatedAt(LocalDateTime.now());

            return reviewRepository.save(oldReview);
        }

        // NEW REVIEW
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    // =========================
    // GET COURSE REVIEWS (DTO RESPONSE)
    // =========================
    public List<ReviewResponseDTO> getReviewsByCourse(String courseId) {

        List<Review> reviews =
                reviewRepository.findByCourseId(courseId);

        return reviews.stream()
                .map(review -> new ReviewResponseDTO(
                        review.getStudentEmail(),
                        review.getCourseId(),
                        review.getRating(),
                        review.getComment(),
                        review.getCreatedAt()
                ))
                .toList();
    }

    // =========================
    // GET AVERAGE RATING
    // =========================
    public double getAverageRating(String courseId) {

        List<Review> reviews =
                reviewRepository.findByCourseId(courseId);

        if (reviews.isEmpty()) {
            return 0.0;
        }

        double sum = reviews.stream()
                .mapToInt(Review::getRating)
                .sum();

        return sum / reviews.size();
    }
 // =========================
 //   Review deleted successfully
 // =========================
    public void deleteReview(String reviewId) {

        reviewRepository.deleteById(reviewId);
    }
}