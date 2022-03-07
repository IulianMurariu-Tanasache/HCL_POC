package hcl.poc.api.productcomposite;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductCompositeAPI {

    @Operation(summary = "Get one aggregated product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "404", description = "Product with specified id not found")
    })
    @GetMapping(value = "/composite-product/{id}", produces = "application/json")
    Mono<ProductAggregate> getOneProduct(@PathVariable("id") Long id);

    @Operation(summary = "Get a list of all aggregated products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping(value = "/composite-product", produces = "application/json")
    Flux<ProductAggregate> getAllProducts();

    @Operation(summary = "Add a new aggregated product with its reviews and recommendation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = "/composite-product", produces = "application/json")
    Mono<ProductAggregate> addOneProduct(@RequestBody ProductAggregate product);

    @Operation(summary = "Update a aggregated product or add one new if the specified id does not exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping(value = "/composite-product", produces = "application/json")
    Mono<ProductAggregate> updateProduct(@PathVariable("id") Long id, @RequestBody ProductAggregate product);

    @Operation(summary = "Delete a product and all of its recommendations and reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/composite-product")
    void deleteOneProduct(@PathVariable("id") Long id);
}
