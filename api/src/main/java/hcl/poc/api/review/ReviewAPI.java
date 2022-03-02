package hcl.poc.api.review;

import hcl.poc.api.user.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewAPI {

    @Operation(summary = "Get a review by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReviewDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Review with specified id not found")
    })
    @GetMapping(value = "/review/{id}", produces = "application/json")
    Mono<ReviewDTO> getOneReview(@PathVariable("id") Long id);

    @Operation(summary = "Get a list of all review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReviewDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping(value = "/review", produces = "application/json")
    Flux<ReviewDTO> getAllReviews();

    @Operation(summary = "Add a new review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReviewDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = "/review", consumes = "application/json")
    void addOneReview(@RequestBody ReviewDTO reviewDTO);

    @Operation(summary = "Update a review by id or add if the review with specified id does not exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReviewDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping(value = "/review/{id}", consumes = "application/json")
    void updateReview(@PathVariable("id") Long id, @RequestBody ReviewDTO reviewDTO);

    @Operation(summary = "Delete a review by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReviewDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/review/{id}")
    void deleteOneReview(@PathVariable("id") Long id);
}
