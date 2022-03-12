package hcl.poc.api.review;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class ReviewDTO {

    @Schema(description = "Unique identifier of the review.",
            example = "1", required = true)
    private Long review_id;

    @Schema(description = "Unique identifier of the reviewed product.",
            example = "1", required = true)
    private Long product_id;

    @Schema(description = "The user that wrote the review.",
            example = "John", required = true)
    private String author;

    @Schema(description = "The subject of the review.",
            example = "Is good", required = true)
    private String subject;

    @Schema(description = "The content of the review.",
            example = "This product is good")
    private String content;

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "review_id=" + review_id +
                ", product_id=" + product_id +
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
        return getReview_id().equals(reviewDTO.getReview_id()) && getProduct_id().equals(reviewDTO.getProduct_id()) && getAuthor().equals(reviewDTO.getAuthor()) && getSubject().equals(reviewDTO.getSubject()) && getContent().equals(reviewDTO.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReview_id(), getProduct_id(), getAuthor(), getSubject(), getContent());
    }

    public Long getReview_id() {
        return review_id;
    }

    public void setReview_id(Long review_id) {
        this.review_id = review_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
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

    public ReviewDTO(Long review_id, Long product_id, String author, String subject, String content) {
        this.review_id = review_id;
        this.product_id = product_id;
        this.author = author;
        this.subject = subject;
        this.content = content;
    }
}
