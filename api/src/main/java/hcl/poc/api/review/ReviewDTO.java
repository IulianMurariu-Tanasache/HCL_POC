package hcl.poc.api.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hcl.poc.api.productcomposite.ProductAggregate;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class ReviewDTO {

    @Schema(description = "Unique identifier of the review.",
            example = "1", required = true)
    private Long reviewId;

    @Schema(description = "Unique identifier of the reviewed product.",
            example = "1", required = true)
    private Long productId;

    @Schema(description = "The user that wrote the review.",
            example = "John", required = true)
    private String author;

    @Schema(description = "The subject of the review.",
            example = "Is good", required = true)
    private String subject;

    @Schema(description = "The content of the review.",
            example = "This product is good")
    private String content;

    @JsonBackReference
    private ProductAggregate productAggregate;

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "review_id=" + reviewId +
                ", product_id=" + productId +
                ", author='" + author + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewDTO)) return false;
        ReviewDTO reviewDTO = (ReviewDTO) o;
        return getReviewId().equals(reviewDTO.getReviewId()) && getProductId().equals(reviewDTO.getProductId()) && getAuthor().equals(reviewDTO.getAuthor()) && getSubject().equals(reviewDTO.getSubject()) && getContent().equals(reviewDTO.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReviewId(), getProductId(), getAuthor(), getSubject(), getContent());
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReviewDTO(){}

    public ReviewDTO(Long reviewId, Long productId, String author, String subject, String content) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.author = author;
        this.subject = subject;
        this.content = content;
    }
}
