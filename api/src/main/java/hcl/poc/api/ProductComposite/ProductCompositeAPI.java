package hcl.poc.api.ProductComposite;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductCompositeAPI {

    @GetMapping("/composite-product")
    Mono<ProductAggregate> getOneProduct(@RequestParam("id") Long id);

    @GetMapping("/composite-product")
    Flux<ProductAggregate> getAllProducts();

    @PostMapping("/composite-product")
    void addOneProduct(@RequestParam("id") Long id, @RequestBody ProductAggregate product);

    @PutMapping("/composite-product")
    void updateProduct(@RequestParam("id") Long id, @RequestBody ProductAggregate product);

    @DeleteMapping("/composite-product")
    void deleteOneProduct(@RequestParam("id") Long id);
}
