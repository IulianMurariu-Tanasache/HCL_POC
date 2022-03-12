package hcl.poc.recommendationservice.controller;

import hcl.poc.api.recommendation.RecommendationAPI;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.recommendationservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RecommendationController implements RecommendationAPI {

    @Autowired
    private RecommendationService service;

    @Override
    public Mono<RecommendationDTO> getOneRecommendation(Long id) {
        return Mono.just(service.getOneRecommendation(id));
    }

    @Override
    public Flux<RecommendationDTO> getAllRecommendations() {
        return Flux.fromIterable(service.getAllRecommendations());
    }

    @Override
    public Flux<RecommendationDTO> getAllRecommendationsForProduct(Long product) {
        return Flux.fromIterable(service.getAllRecommendationsForProduct(product));
    }

    @Override
    public Mono<RecommendationDTO> addOneRecommendation(RecommendationDTO recommendationDTO) {
        return Mono.just(service.addOneRecommendation(recommendationDTO));
    }

    @Override
    public Mono<RecommendationDTO> updateRecommendation(Long id, RecommendationDTO recommendationDTO) {
        return Mono.just(service.modifyRecommendation(id, recommendationDTO));
    }

    @Override
    public void deleteOneRecommendation(Long id) {
        service.deleteOneRecommendation(id);
    }
}
