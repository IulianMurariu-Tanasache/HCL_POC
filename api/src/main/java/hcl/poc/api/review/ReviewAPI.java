package hcl.poc.api.review;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewAPI {

    @GetMapping("/review/{id}")
    Mono<ReviewDTO> getOneReview(@PathVariable("id") Long id);

    @GetMapping("/review")
    Flux<ReviewDTO> getAllReviews();

    @PostMapping("/review")
    void addOneReview(@RequestBody ReviewDTO reviewDTO);

    @PutMapping("/review/{id}")
    void updateReview(@PathVariable("id") Long id, @RequestBody ReviewDTO reviewDTO);

    @DeleteMapping("/review/{id}")
    void deleteOneReview(@PathVariable("id") Long id);
}
