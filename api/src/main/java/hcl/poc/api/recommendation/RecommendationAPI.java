package hcl.poc.api.recommendation;

import hcl.poc.api.product.ProductDTO;
import hcl.poc.api.productcomposite.ProductAggregate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecommendationAPI {

    @Operation(summary = "Get one recommendation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "404", description = "Recommendation with specified id not found")
    })
    @GetMapping(value = "/recommendation/{id}", produces = "application/json")
    Mono<RecommendationDTO> getOneRecommendation(@PathVariable("id") Long id);

    @Operation(summary = "Get a list of all recommendations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping(value = "/recommendation", produces = "application/json")
    Flux<RecommendationDTO> getAllRecommendations();

    @Operation(summary = "Add a new recommendation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = "/recommendation", consumes = "application/json")
    void addOneRecommendation(@RequestBody RecommendationDTO recommendationDTO);

    @Operation(summary = "Update a recommendation by id or add a new one if the specified id does not exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping(value = "/recommendation/{id}", consumes = "application/json")
    void updateRecommendation(@PathVariable("id") Long id, @RequestBody RecommendationDTO recommendationDTO);

    @Operation(summary = "Delete one recommendation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/recommendation/{id}")
    void deleteOneRecommendation(@PathVariable("id") Long id);
}
