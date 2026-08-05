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

    // ADD / UPDATE REVIEW
    public Review addReview(Review review) {

        Optional<Review> existingReview =
                reviewRepository.findByCourseIdAndStudentEmail(
                        review.getCourseId(),
                        review.getStudentEmail());

        if (existingReview.isPresent()) {

            Review oldReview = existingReview.get();

            oldReview.setRating(review.getRating());
            oldReview.setComment(review.getComment());
            oldReview.setCreatedAt(LocalDateTime.now());

            return reviewRepository.save(oldReview);
        }

        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    // GET COURSE REVIEWS
    public List<ReviewResponseDTO> getReviewsByCourse(String courseId) {

        return reviewRepository.findByCourseId(courseId)
                .stream()
                .map(review -> new ReviewResponseDTO(
                        review.getStudentEmail(),
                        review.getCourseId(),
                        review.getRating(),
                        review.getComment(),
                        review.getCreatedAt()))
                .toList();
    }

    // AVERAGE RATING
    public double getAverageRating(String courseId) {

        List<Review> reviews =
                reviewRepository.findByCourseId(courseId);

        if (reviews.isEmpty()) {
            return 0;
        }

        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);
    }

    // GET ALL
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    // DELETE
    public void delete(String id) {
        reviewRepository.deleteById(id);
    }

    // DELETE (Old Method)
    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}