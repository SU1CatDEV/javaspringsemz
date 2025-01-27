package su1cat.sem9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import su1cat.sem9.model.Review;
import su1cat.sem9.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/byproduct/{productId}")
    public List<Review> findByProduct(@PathVariable("productId") Long productId) {
        System.out.println("by product filter");
        return reviewService.reviewsForProduct(productId);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addReview(@ModelAttribute Review review) {
        if (review.hasThisNulls()) {
            return ResponseEntity.badRequest().body("Review has null values");
        }
        if (review.getText().length() > 100) {
            return ResponseEntity.badRequest().body("Review text exceeds 100 characters");
        }
        if (review.getRating() > 5 || review.getRating() < 0) {
            return ResponseEntity.badRequest().body("Invalid review rating");
        }
        Review createdReview = reviewService.createReview(review);
        String redirectUrl = "/products/" + review.getProduct().toString();
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
    }

    // todo : add update functionality eventually. tbh crud projects arent hard theyre just annoying


    @GetMapping("/delete/{reviewId}")
    public void removeReview(@PathVariable("reviewId") Long reviewId) {
        if (!reviewService.exists(reviewId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review does not exist");
        }
        reviewService.deleteProduct(reviewId);
    }
}

