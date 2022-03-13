package hcl.poc.api.recommendation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hcl.poc.api.productcomposite.ProductAggregate;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class RecommendationDTO {

    @Schema(description = "Unique identifier of the recommendation.",
            example = "1", required = true)
    private Long recommendationId;

    @Schema(description = "Unique identifier of the recommended product.",
            example = "1", required = true)
    private Long productId;

    @Schema(description = "The user that wrote the recommendation.",
            example = "John", required = true)
    private String author;

    @Schema(description = "How good the user thinks the product is.",
            example = "4.5", required = true)
    private double rate;

    @Schema(description = "The content of the recommendation.",
            example = "This product works well")
    private String content;

    @JsonBackReference
    private ProductAggregate productAggregate;

    public RecommendationDTO(){
    }

    public RecommendationDTO(Long productId, Long recommendationId, String author, double rate, String content) {
        this.productId = productId;
        this.recommendationId = recommendationId;
        this.author = author;
        this.rate = rate;
        this.content = content;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Long recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecommendationDTO)) return false;
        RecommendationDTO that = (RecommendationDTO) o;
        return Double.compare(that.getRate(), getRate()) == 0 && getProductId().equals(that.getProductId()) && getRecommendationId().equals(that.getRecommendationId()) && getAuthor().equals(that.getAuthor()) && getContent().equals(that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getRecommendationId(), getAuthor(), getRate(), getContent());
    }

    @Override
    public String toString() {
        return "RecommendationDTO{" +
                "product_id=" + productId +
                ", recommendation_id=" + recommendationId +
                ", author='" + author + '\'' +
                ", rate=" + rate +
                ", content='" + content + '\'' +
                '}';
    }
}
