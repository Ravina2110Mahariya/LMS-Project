package com.LMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.Review;
import com.LMS.Service.ReviewService;
import com.LMS.dto.ReviewResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // ==============================
    // 1. ADD REVIEW (POST)
    // ==============================
    @PostMapping
    public ResponseEntity<Review> addReview(
            @RequestBody Review review,
            Authentication authentication
    ) {
        // Set logged-in user email
        review.setStudentEmail(authentication.getName());

        Review savedReview = reviewService.addReview(review);
        return ResponseEntity.ok(savedReview);
    }

    // ==============================
    // 2. GET REVIEWS BY COURSE (GET)
    // ==============================
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByCourse(
            @PathVariable String courseId
    ) {
        return ResponseEntity.ok(
                reviewService.getReviewsByCourse(courseId)
        );
    }
    // ==============================
    // 3. GET AVERAGE RATING (GET)
    // ==============================
    @GetMapping("/course/{courseId}/average")
    public ResponseEntity<Double> getAverageRating(
            @PathVariable String courseId
    ) {
        return ResponseEntity.ok(
                reviewService.getAverageRating(courseId)
        );
    }
    
 // ==============================
    // 4. DELETE REVIEW
    // ==============================
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable String reviewId
    ) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted successfully");
    }
        
    
}