package hcl.poc.recommendationservice.repository;

import hcl.poc.recommendationservice.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
//    @Query(
//            "select r from RECOMMENDATION r where r.product_id = :id"
//    )
    List<Recommendation> findAllByProductId(@Param("id") Long id);
}
