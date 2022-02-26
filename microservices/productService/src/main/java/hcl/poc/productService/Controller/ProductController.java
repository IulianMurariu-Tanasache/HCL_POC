package hcl.poc.productService.Controller;

import hcl.poc.api.Product.ProductDTO;
import hcl.poc.api.Product.ProductAPI;
import hcl.poc.productService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController implements ProductAPI {

    @Autowired
    private ProductService service;

    @Override
    public Mono<ProductDTO> getOneProduct(Long id) {
        return Mono.just(service.getOneProduct(id));
    }

    @Override
    public Flux<ProductDTO> getAllProducts() {
        return Flux.fromIterable(service.getAllProducts());
    }

    @Override
    public void addOneProduct( ProductDTO productDTO) {
        service.addProduct(productDTO);
    }
    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        service.modifyProduct(id, productDTO);
    }

    @Override
    public void deleteOneProduct(Long id) {
        service.deleteProduct(id);
    }
}
