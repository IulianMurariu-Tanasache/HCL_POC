import hcl.poc.api.product.ProductDTO;
import hcl.poc.productservice.controller.ProductController;
import hcl.poc.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(ProductController.class)
@ContextConfiguration(classes = {hcl.poc.productservice.ProductServiceApplication.class})
public class ProductControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ProductService service;

    ProductDTO p1 = new ProductDTO(1L, "milk", 20.55);
    ProductDTO p2 = new ProductDTO(2L, "cheese", 10.12);
    ProductDTO p3 = new ProductDTO(3L, "fridge", 20000.14);

    @Test
    void getAllProducts_success() throws Exception {
        List<ProductDTO> products = new ArrayList<>(Arrays.asList(p1, p2, p3));

        Mockito.when(service.getAllProducts()).thenReturn(products);

        webClient.get()
                .uri("/product")
                .header(HttpHeaders.ACCEPT,"application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProductDTO.class).isEqualTo(products);
    }

    @Test
    void getProductByID_success() {
        Mockito.when(service.getOneProduct(p1.getId())).thenReturn(p1);

        webClient.get()
                .uri("/product/1")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDTO.class).isEqualTo(p1);
    }

    @Test
    void postProduct_successs() {
        Mockito.when(service.addProduct(p1)).thenReturn(p1);

        webClient.post()
                .uri("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(p1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDTO.class).isEqualTo(p1);

        Mockito.verify(service).addProduct(p1);
    }

    @Test
    void putProduct_succes() {
        Mockito.when(service.modifyProduct(p1.getId(),p2)).thenReturn(p2);

        webClient.put()
                .uri("/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(p2)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDTO.class).isEqualTo(p2);

        Mockito.verify(service).modifyProduct(p1.getId(), p2);
    }

    @Test
    void deleteProduct_succes() {
        Mockito.doNothing().when(service).deleteProduct(p1.getId());

        webClient.delete()
                .uri("/product/1")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(service).deleteProduct(p1.getId());
    }
}
