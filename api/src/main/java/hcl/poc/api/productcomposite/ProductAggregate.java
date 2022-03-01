package hcl.poc.api.productcomposite;

import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;

import java.util.List;
import java.util.Objects;

public class ProductAggregate {

    private Long id;
    private String name;
    private double weight;
    private List<RecommendationDTO> recommendationDTOS;
    private List<ReviewDTO> reviewDTOS;

    @Override
    public String toString() {
        return "ProductAggregate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", recommendations=" + recommendationDTOS +
                ", reviews=" + reviewDTOS +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductAggregate)) return false;
        ProductAggregate that = (ProductAggregate) o;
        return Double.compare(that.getWeight(), getWeight()) == 0 && getId().equals(that.getId()) && getName().equals(that.getName()) && getRecommendations().equals(that.getRecommendations()) && getReviews().equals(that.getReviews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getWeight(), getRecommendations(), getReviews());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<RecommendationDTO> getRecommendations() {
        return recommendationDTOS;
    }

    public void setRecommendations(List<RecommendationDTO> recommendationDTOS) {
        this.recommendationDTOS = recommendationDTOS;
    }

    public List<ReviewDTO> getReviews() {
        return reviewDTOS;
    }

    public void setReviews(List<ReviewDTO> reviewDTOS) {
        this.reviewDTOS = reviewDTOS;
    }

    public ProductAggregate(Long id, String name, double weight, List<RecommendationDTO> recommendationDTOS, List<ReviewDTO> reviewDTOS) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.recommendationDTOS = recommendationDTOS;
        this.reviewDTOS = reviewDTOS;
    }

    public ProductAggregate() {
    }
}
