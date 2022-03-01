package hcl.poc.api.recommendation;

import hcl.poc.api.product.ProductDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecommendationAPI {

    @GetMapping("/recommendation/{id}")
    Mono<RecommendationDTO> getOneRecommendation(@PathVariable("id") Long id);

    @GetMapping("/recommendation")
    Flux<RecommendationDTO> getAllRecommendations();

    @PostMapping("/recommendation")
    void addOneRecommendation(@RequestBody RecommendationDTO recommendationDTO);

    @PutMapping("/recommendation/{id}")
    void updateRecommendation(@PathVariable("id") Long id, @RequestBody RecommendationDTO recommendationDTO);

    @DeleteMapping("/recommendation/{id}")
    void deleteOneRecommendation(@PathVariable("id") Long id);
}
