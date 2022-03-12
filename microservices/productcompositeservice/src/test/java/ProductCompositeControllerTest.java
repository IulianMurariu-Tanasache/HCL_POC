import hcl.poc.api.product.ProductDTO;
import hcl.poc.api.productcomposite.ProductAggregate;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;
import hcl.poc.productcompositeservice.controller.ProductCompositeController;
import hcl.poc.productcompositeservice.service.ProductCompositeService;
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
@WebFluxTest(ProductCompositeController.class)
@ContextConfiguration(classes = {hcl.poc.productcompositeservice.ProductCompositeServiceApplication.class})
public class ProductCompositeControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ProductCompositeService service;

    private final ProductDTO p1 = new ProductDTO(1L, "milk", 20.55);
    private final ProductDTO p2 = new ProductDTO(1L, "cheese", 35.74);

    private final ReviewDTO review1P1 = new ReviewDTO(1L, 1L, "First Author", "First Subject", "The content");
    private final ReviewDTO review2P1 = new ReviewDTO(2L, 1L, "Second Author", "Second Subject", "The content");
    private final ReviewDTO review1P2 = new ReviewDTO(3L, 2L, "Second Author", "Second Subject", "The content");
    private final ReviewDTO review2P2 = new ReviewDTO(4L, 2L, "Third Author", "Third Subject", "The content");

    private final RecommendationDTO recommendation1P1 = new RecommendationDTO(1L, 1L, "First Author", 3.75, "This is the content");
    private final RecommendationDTO recommendation2P1 = new RecommendationDTO(1L, 2L, "Second Author", 4.75, "This is the content");
    private final RecommendationDTO recommendation1P2 = new RecommendationDTO(2L, 3L, "Second Author", 4.75, "This is the content");
    private final RecommendationDTO recommendation2P2 = new RecommendationDTO(2L, 4L, "Third Author", 4.11, "This is the content");

    private final List<RecommendationDTO> recommendationDTOListP1 = new ArrayList<>(Arrays.asList(recommendation1P1, recommendation2P1));
    private final List<RecommendationDTO> recommendationDTOListP2 = new ArrayList<>(Arrays.asList(recommendation1P2,recommendation2P2));
    private final List<ReviewDTO> reviewDTOListP1 = new ArrayList<>(Arrays.asList(review1P1,review2P1));
    private final List<ReviewDTO> reviewDTOListP2 = new ArrayList<>(Arrays.asList(review1P2,review2P2));

    private final ProductAggregate productAggregate1 = new ProductAggregate(
            1L,
            "milk",
            20.55,
            recommendationDTOListP1,
            reviewDTOListP1
    );

    private final ProductAggregate productAggregate2 = new ProductAggregate(
            2L,
            "cheese",
            35.74,
            recommendationDTOListP2,
            reviewDTOListP2
    );

    @Test
    void getAllProductsSuccess() throws Exception {
        List<ProductAggregate> products = new ArrayList<>(Arrays.asList(productAggregate1, productAggregate2));

        Mockito.when(service.getAllProductComposite()).thenReturn(products);

        webClient.get()
                .uri("/composite-product")
                .header(HttpHeaders.ACCEPT,"application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProductAggregate.class).isEqualTo(products);
    }

    @Test
    void getProductByIDSuccess() {
        Mockito.when(service.getProductComposite(productAggregate1.getId())).thenReturn(productAggregate1);

        webClient.get()
                .uri("/composite-product/1")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductAggregate.class).isEqualTo(productAggregate1);
    }

    @Test
    void postProductSuccesss() {
        Mockito.when(service.addProductComposite(productAggregate1)).thenReturn(productAggregate1);

        webClient.post()
                .uri("/composite-product")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(productAggregate1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductAggregate.class).isEqualTo(productAggregate1);

        Mockito.verify(service).addProductComposite(productAggregate1);
    }

//    @Test
//    void putProductSuccess() {
//        Mockito.when(service.modifyProduct(p1.getId(),p2)).thenReturn(p2);
//
//        webClient.put()
//                .uri("/product/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(p2)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(ProductDTO.class).isEqualTo(p2);
//
//        Mockito.verify(service).modifyProduct(p1.getId(), p2);
//    }

    @Test
    void deleteProductSuccess() {
        Mockito.doNothing().when(service).deleteProductComposite(productAggregate1.getId());

        webClient.delete()
                .uri("/composite-product/1")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(service).deleteProductComposite(productAggregate1.getId());
    }
}
