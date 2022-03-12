package hcl.poc.productcompositeservice.controller;

import hcl.poc.api.productcomposite.ProductAggregate;
import hcl.poc.api.productcomposite.ProductCompositeAPI;
import hcl.poc.productcompositeservice.service.ProductCompositeService;
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
        return Flux.fromIterable(service.getAllProductComposite());
    }

    @Override
    public Mono<ProductAggregate> addOneProduct(ProductAggregate product) {
        return Mono.just(service.addProductComposite(product));
    }

    @Override
    public Mono<ProductAggregate> updateProduct(Long id, ProductAggregate product) {
        return Mono.just(service.updateProductComposite(id, product));
    }

    @Override
    public void deleteOneProduct(Long id) {
        service.deleteProductComposite(id);
    }
}
