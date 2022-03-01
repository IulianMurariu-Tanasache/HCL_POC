package hcl.poc.recommendationService.controller;

import hcl.poc.api.recommendation.RecommendationAPI;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.recommendationService.service.RecommendationService;
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
        return Flux.fromIterable(service.getAllUser());
    }

    @Override
    public void addOneRecommendation(RecommendationDTO recommendationDTO) {
        service.addOneRecommendation(recommendationDTO);
    }

    @Override
    public void updateRecommendation(Long id, RecommendationDTO recommendationDTO) {
        service.modifyRecommendation(id, recommendationDTO);
    }

    @Override
    public void deleteOneRecommendation(Long id) {
        service.deleteOneRecommendation(id);
    }
}
