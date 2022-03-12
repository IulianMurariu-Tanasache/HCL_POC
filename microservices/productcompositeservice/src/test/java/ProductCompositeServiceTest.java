import hcl.poc.api.product.ProductDTO;
import hcl.poc.api.productcomposite.ProductAggregate;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;
import hcl.poc.productcompositeservice.integration.ProductCompositeIntegration;
import hcl.poc.productcompositeservice.service.ProductCompositeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {hcl.poc.productcompositeservice.ProductCompositeServiceApplication.class})
public class ProductCompositeServiceTest {

    @MockBean
    private ProductCompositeIntegration integration;

    @InjectMocks
    private ProductCompositeService service;

    private final ProductDTO p1 = new ProductDTO(1L, "milk", 20.55);
    private final ProductDTO p2 = new ProductDTO(1L, "cheese", 35.74);

    private final ReviewDTO review1P1 = new ReviewDTO(1L, 1L, "First Author", "First Subject", "The content");
    private final ReviewDTO review2P1 = new ReviewDTO(2L, 1L, "Second Author", "Second Subject", "The content");
    private final ReviewDTO review1P2 = new ReviewDTO(3L, 1L, "Second Author", "Second Subject", "The content");
    private final ReviewDTO review2P2 = new ReviewDTO(4L, 1L, "Third Author", "Third Subject", "The content");

    private final RecommendationDTO recommendation1P1 = new RecommendationDTO(1L, 1L, "First Author", 3.75, "This is the content");
    private final RecommendationDTO recommendation2P1 = new RecommendationDTO(1L, 2L, "Second Author", 4.75, "This is the content");
    private final RecommendationDTO recommendation1P2 = new RecommendationDTO(1L, 3L, "Second Author", 4.75, "This is the content");
    private final RecommendationDTO recommendation2P2 = new RecommendationDTO(1L, 4L, "Third Author", 4.11, "This is the content");

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
            1L,
            "cheese",
            35.74,
            recommendationDTOListP2,
            reviewDTOListP2
    );

    @Test
    void createProductAggregateTest() {
        assertEquals(service.createProductAggregate(p1, reviewDTOListP1, recommendationDTOListP1), productAggregate1);
    }

    @Test
    void getProductCompositeTest() {
        Mockito.when(integration.getProduct(1L)).thenReturn(p1);
        Mockito.when(integration.getReviewsForProduct(1L)).thenReturn(reviewDTOListP1);
        Mockito.when(integration.getRecommendationsForProduct(1L)).thenReturn(recommendationDTOListP1);

        assertEquals(service.getProductComposite(1L), productAggregate1);
    }

    @Test
    void getAllProductCompositeTest() {
        Mockito.when(integration.getProducts()).thenReturn(Collections.singletonList(p1));
        Mockito.when(integration.getReviewsForProduct(1L)).thenReturn(reviewDTOListP1);
        Mockito.when(integration.getRecommendationsForProduct(1L)).thenReturn(recommendationDTOListP1);

        assertEquals(service.getAllProductComposite(), Collections.singletonList(productAggregate1));
    }

    @Test
    void addProductCompositeTest() {
        Mockito.when(integration.addProduct(p1)).thenReturn(p1);
        Mockito.when(integration.addReview(review1P1, 1L)).thenReturn(review1P1);
        Mockito.when(integration.addReview(review2P1, 1L)).thenReturn(review2P1);
        Mockito.when(integration.addRecommendation(recommendation1P1, 1L)).thenReturn(recommendation1P1);
        Mockito.when(integration.addRecommendation(recommendation2P1, 1L)).thenReturn(recommendation2P1);

        assertEquals(service.addProductComposite(productAggregate1), productAggregate1);
    }

//    @Test
//    void updateProductCompositeTest() {
//        Mockito.when(integration.updateProduct(p2, 1L)).thenReturn(p2);
//        Mockito.when(integration.updateReview(review1P2, 1L, 1L)).thenReturn(review1P2);
//        Mockito.when(integration.updateReview(review2P2, 2L, 1L)).thenReturn(review2P2);
//        Mockito.when(integration.updateRecommendation(recommendation1P2, 1L, 1L)).thenReturn(recommendation1P2);
//        Mockito.when(integration.updateRecommendation(recommendation2P2, 2L, 1L)).thenReturn(recommendation2P2);
//
//        assertEquals(service.updateProductComposite(1L, productAggregate2), productAggregate2);
//    }

    @Test
    void deleteProductCompositeTest(){
        Mockito.doNothing().when(integration).deleteProduct(1L);
        Mockito.doNothing().when(integration).deleteReview(1L);
        Mockito.doNothing().when(integration).deleteReview(2L);
        Mockito.doNothing().when(integration).deleteRecommendation(1L);
        Mockito.doNothing().when(integration).deleteRecommendation(2L);
        Mockito.when(integration.getRecommendationsForProduct(1L)).thenReturn(recommendationDTOListP1);
        Mockito.when(integration.getReviewsForProduct(1L)).thenReturn(reviewDTOListP1);

        service.deleteProductComposite(1L);

        Mockito.verify(integration).deleteProduct(1L);
        Mockito.verify(integration).deleteReview(1L);
        Mockito.verify(integration).deleteReview(2L);
        Mockito.verify(integration).deleteRecommendation(1L);
        Mockito.verify(integration).deleteRecommendation(2L);
    }
}
