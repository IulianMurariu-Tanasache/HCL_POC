package hcl.poc.recommendationservice.repository;

import hcl.poc.recommendationservice.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}
