package hcl.poc.api.ProductComposite;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductCompositeAPI {

    @GetMapping("/composite-product")
    Mono<ProductAggregate> getOneProduct(@PathVariable("id") Long id);

    @GetMapping("/composite-product")
    Flux<ProductAggregate> getAllProducts();

    @PostMapping("/composite-product")
    void addOneProduct(@PathVariable("id") Long id, @RequestBody ProductAggregate product);

    @PutMapping("/composite-product")
    void updateProduct(@PathVariable("id") Long id, @RequestBody ProductAggregate product);

    @DeleteMapping("/composite-product")
    void deleteOneProduct(@PathVariable("id") Long id);
}
