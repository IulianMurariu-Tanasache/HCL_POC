package hcl.poc.reviewService.controller;

import hcl.poc.api.review.ReviewAPI;
import hcl.poc.api.review.ReviewDTO;
import hcl.poc.reviewService.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReviewController implements ReviewAPI {

    @Autowired
    private ReviewService service;

    @Override
    public Mono<ReviewDTO> getOneReview(Long id) {
        return Mono.just(service.getOneReview(id));
    }

    @Override
    public Flux<ReviewDTO> getAllReviews() {
        return Flux.fromIterable(service.getAllReviews());
    }

    @Override
    public void addOneReview(ReviewDTO reviewDTO) {
        service.addOneReview(reviewDTO);
    }

    @Override
    public void updateReview(Long id, ReviewDTO reviewDTO) {
        service.modifyReview(id, reviewDTO);
    }

    @Override
    public void deleteOneReview(Long id) {
        service.deleteOneReview(id);
    }
}
