package hcl.poc.productCompositeService.controller;

import hcl.poc.api.productcomposite.ProductAggregate;
import hcl.poc.api.productcomposite.ProductCompositeAPI;
import hcl.poc.productCompositeService.service.ProductCompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductCompositeController implements ProductCompositeAPI {

    @Autowired
    private ProductCompositeService service;

    @Override
    public Mono<ProductAggregate> getOneProduct(Long id) {
        return Mono.just(service.getProductComposite(id));
    }

    @Override
    public Flux<ProductAggregate> getAllProducts() {
        return null;
    }

    @Override
    public void addOneProduct(Long id, ProductAggregate product) {

    }

    @Override
    public void updateProduct(Long id, ProductAggregate product) {

    }

    @Override
    public void deleteOneProduct(Long id) {

    }
}
