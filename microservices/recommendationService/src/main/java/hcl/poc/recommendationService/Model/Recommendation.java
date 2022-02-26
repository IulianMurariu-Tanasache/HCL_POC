package hcl.poc.recommendationService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    private Long product_id;
    private Long recommendation_id;
    private String author;
    private double rate;
    private String content;
}
