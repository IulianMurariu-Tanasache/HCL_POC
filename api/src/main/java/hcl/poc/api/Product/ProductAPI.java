package hcl.poc.api.Product;


import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductAPI {

    @GetMapping("/product")
    Mono<Product> getOneProduct(@RequestParam("id") Long id);

    @GetMapping("/product")
    Flux<Product> getAllProducts();

    @PostMapping("/product")
    void addOneProduct(@RequestParam("id") Long id, @RequestBody Product product);

    @PutMapping("/product")
    void updateProduct(@RequestParam("id") Long id, @RequestBody Product product);

    @DeleteMapping("/product")
    void deleteOneProduct(@RequestParam("id") Long id);
}
