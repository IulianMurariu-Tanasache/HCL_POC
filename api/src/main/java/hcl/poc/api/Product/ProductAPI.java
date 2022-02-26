package hcl.poc.api.Product;


import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductAPI {

    @GetMapping("/product/{id}")
    Mono<ProductDTO> getOneProduct(@PathVariable("id") Long id);

    @GetMapping("/product")
    Flux<ProductDTO> getAllProducts();

    @PostMapping("/product")
    void addOneProduct(@RequestBody ProductDTO productDTO);

    @PutMapping("/product/{id}")
    void updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO);

    @DeleteMapping("/product/{id}")
    void deleteOneProduct(@PathVariable("id") Long id);
}
