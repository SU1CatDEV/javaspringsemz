package su1cat.sem9.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su1cat.sem9.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAllByProduct(Long productId);
}
