package hcl.poc.api.recommendation;

import java.util.Objects;

public class RecommendationDTO {

    private Long product_id;
    private Long recommendation_id;
    private String author;
    private double rate;
    private String content;

    public RecommendationDTO(){
    }

    public RecommendationDTO(Long product_id, Long recommendation_id, String author, double rate, String content) {
        this.product_id = product_id;
        this.recommendation_id = recommendation_id;
        this.author = author;
        this.rate = rate;
        this.content = content;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getRecommendation_id() {
        return recommendation_id;
    }

    public void setRecommendation_id(Long recommendation_id) {
        this.recommendation_id = recommendation_id;
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
        return Double.compare(that.getRate(), getRate()) == 0 && getProduct_id().equals(that.getProduct_id()) && getRecommendation_id().equals(that.getRecommendation_id()) && getAuthor().equals(that.getAuthor()) && getContent().equals(that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct_id(), getRecommendation_id(), getAuthor(), getRate(), getContent());
    }

    @Override
    public String toString() {
        return "RecommendationDTO{" +
                "product_id=" + product_id +
                ", recommendation_id=" + recommendation_id +
                ", author='" + author + '\'' +
                ", rate=" + rate +
                ", content='" + content + '\'' +
                '}';
    }
}
