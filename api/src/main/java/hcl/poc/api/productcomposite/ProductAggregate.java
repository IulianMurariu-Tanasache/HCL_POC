package hcl.poc.api.productcomposite;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductAggregate {

    @Schema(description = "Unique identifier of the product.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "The name of the product",
            example = "milk", required = true)
    private String name;

    @Schema(description = "The weight of the product.",
            example = "250.22", required = true)
    private double weight;

    @Schema(description = "A list of recommendations for this product."
    )
    @JsonManagedReference
    private List<RecommendationDTO> recommendationList;

    @Schema(description = "A list of reviews for this product."
    )
    @JsonManagedReference
    private List<ReviewDTO> reviewList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductAggregate)) return false;
        ProductAggregate that = (ProductAggregate) o;
        return Double.compare(that.getWeight(), getWeight()) == 0 && getId().equals(that.getId()) && getName().equals(that.getName()) && getRecommendationList().equals(that.getRecommendationList()) && getReviewList().equals(that.getReviewList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getWeight(), getRecommendationList(), getReviewList());
    }

    @Override
    public String toString() {
        return "ProductAggregate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", recommendationList=" + recommendationList +
                ", reviewList=" + reviewList +
                '}';
    }

    public List<RecommendationDTO> getRecommendationList() {
        return recommendationList;
    }

    public void setRecommendationList(List<RecommendationDTO> recommendationList) {
        this.recommendationList = new ArrayList<>();
        this.recommendationList.addAll(recommendationList);
    }

    public List<ReviewDTO> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewDTO> reviewList) {
        this.reviewList = new ArrayList<>();
        this.reviewList.addAll(reviewList);
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

    public ProductAggregate(Long id, String name, double weight, List<RecommendationDTO> recommendationList, List<ReviewDTO> reviewList) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.recommendationList = new ArrayList<>();
        this.recommendationList.addAll(recommendationList);
        this.reviewList = new ArrayList<>();
        this.reviewList.addAll(reviewList);
    }

    public ProductAggregate() {
    }
}
