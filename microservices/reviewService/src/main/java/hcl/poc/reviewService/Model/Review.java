package hcl.poc.reviewService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long product_id;
    private Long review_id;
    private String author;
    private String subject;
    private String content;
}
