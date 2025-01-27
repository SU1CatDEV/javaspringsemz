package su1cat.sem9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su1cat.sem9.model.Review;
import su1cat.sem9.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {
    // crud + by product

    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Review moddedReview) {
        return reviewRepository.save(moddedReview);
    }

    public void deleteProduct(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        }
    }

    public List<Review> reviewsForProduct(Long productId) {
        return reviewRepository.findAllByProduct(productId);
    }

    public Boolean exists(Long id) {
        return reviewRepository.existsById(id);
    }
}
