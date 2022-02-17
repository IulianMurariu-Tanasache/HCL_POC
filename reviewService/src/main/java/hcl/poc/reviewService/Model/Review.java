package hcl.poc.reviewService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Integer product_id;
    private Integer review_id;
    private String author;
    private String subject;
    private String content;
}
