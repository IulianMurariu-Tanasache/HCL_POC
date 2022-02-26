package hcl.poc.api.ProductComposite;

import hcl.poc.api.Recommendation.Recommendation;
import hcl.poc.api.Review.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAggregate {

    private Long id;
    private String name;
    private double weight;
    private List<Recommendation> recommendations;
    private List<Review> reviews;
}
