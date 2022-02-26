package hcl.poc.api.ProductComposite;

import hcl.poc.api.Recommendation.Recommendation;
import hcl.poc.api.Review.Review;

import java.util.List;
import java.util.Objects;

public class ProductAggregate {

    private Long id;
    private String name;
    private double weight;
    private List<Recommendation> recommendations;
    private List<Review> reviews;

    @Override
    public String toString() {
        return "ProductAggregate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", recommendations=" + recommendations +
                ", reviews=" + reviews +
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

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public ProductAggregate(Long id, String name, double weight, List<Recommendation> recommendations, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.recommendations = recommendations;
        this.reviews = reviews;
    }

    public ProductAggregate() {
    }
}
