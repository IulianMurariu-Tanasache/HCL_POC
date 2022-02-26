package hcl.poc.productService.Controller;

import hcl.poc.api.Product.Product;
import hcl.poc.api.Product.ProductAPI;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController implements ProductAPI {
    
    @Override
    public Mono<Product> getOneProduct(Long id) {
        return null;
    }

    @Override
    public Flux<Product> getAllProducts() {
        return null;
    }

    @Override
    public void addOneProduct(Long id, Product product) {

    }

    @Override
    public void updateProduct(Long id, Product product) {

    }

    @Override
    public void deleteOneProduct(Long id) {

    }
}
