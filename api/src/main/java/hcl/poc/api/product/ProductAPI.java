package hcl.poc.api.product;


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

public interface ProductAPI {

    @Operation(summary = "Get a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "404", description = "The product with specified id does not exist")
    })
    @GetMapping(value = "/product/{id}", produces = "application/json")
    Mono<ProductDTO> getOneProduct(@PathVariable("id") Long id);

    @Operation(summary = "Get a list of all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping(value = "/product", produces = "application/json")
    Flux<ProductDTO> getAllProducts();

    @Operation(summary = "Add a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = "/product", consumes = "application/json")
    Mono<ProductDTO> addOneProduct(@RequestBody ProductDTO productDTO);

    @Operation(summary = "Update a product by id or add a new one if the specified id does not exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping(value = "/product/{id}", consumes = "application/json")
    Mono<ProductDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO);

    @Operation(summary = "Delete a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductAggregate.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/product/{id}")
    void deleteOneProduct(@PathVariable("id") Long id);
}
