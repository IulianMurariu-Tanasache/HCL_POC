package hcl.poc.reviewservice.repository;

import hcl.poc.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(
            "select review from REVIEW review where review.product_id = :id"
    )
    List<Review> findAllByProductId(@Param("id") Long id);
}
