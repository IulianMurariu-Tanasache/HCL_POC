import com.fasterxml.jackson.databind.ObjectMapper;
import hcl.poc.api.product.ProductDTO;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;
import hcl.poc.productcompositeservice.integration.ProductCompositeIntegration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {hcl.poc.productcompositeservice.ProductCompositeServiceApplication.class})
public class ProductCompositeIntegrationTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper mapper;

    private ProductCompositeIntegration integration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        integration = new ProductCompositeIntegration(
                restTemplate,
                mapper,
                "localhost",
                7001,
                "localhost", 7003,
                "localhost",
                7002);
    }

    private final String productServiceUrl = "http://localhost:7001/product";
    private final String recommendationServiceUrl = "http://localhost:7003/recommendation";
    private final String reviewServiceUrl = "http://localhost:7002/review";

    private final ProductDTO p1 = new ProductDTO(1L, "milk", 20.55);
    private final ProductDTO p2 = new ProductDTO(2L, "cheese", 35.74);

    private final ReviewDTO review1P1 = new ReviewDTO(1L, 1L, "First Author", "First Subject", "The content");
    private final ReviewDTO review2P1 = new ReviewDTO(2L, 1L, "Second Author", "Second Subject", "The content");
    private final ReviewDTO review1P2 = new ReviewDTO(3L, 2L, "Second Author", "Second Subject", "The content");
    private final ReviewDTO review2P2 = new ReviewDTO(4L, 2L, "Third Author", "Third Subject", "The content");

    private final RecommendationDTO recommendation1P1 = new RecommendationDTO(1L, 1L, "First Author", 3.75, "This is the content");
    private final RecommendationDTO recommendation2P1 = new RecommendationDTO(1L, 2L, "Second Author", 4.75, "This is the content");
    private final RecommendationDTO recommendation1P2 = new RecommendationDTO(2L, 3L, "Second Author", 4.75, "This is the content");
    private final RecommendationDTO recommendation2P2 = new RecommendationDTO(2L, 4L, "Third Author", 4.11, "This is the content");

    private final List<RecommendationDTO> recommendationDTOListP1 = new ArrayList<>(Arrays.asList(recommendation1P1, recommendation2P1));
    private final List<RecommendationDTO> recommendationDTOListP2 = new ArrayList(Arrays.asList(recommendation1P2,recommendation2P2));
    private final List<ReviewDTO> reviewDTOListP1 = new ArrayList(Arrays.asList(review1P1,review2P1));
    private final List<ReviewDTO> reviewDTOListP2 = new ArrayList(Arrays.asList(review1P2,review2P2));

    @Test
    void getProductSuccess() {
        Mockito.when(restTemplate.getForObject(productServiceUrl + "/1", ProductDTO.class)).thenReturn(p1);

        assertEquals(integration.getProduct(1L), p1);
    }

    @Test
    void getProductsSuccess() {
        Mockito.when(restTemplate.getForObject(productServiceUrl, ProductDTO[].class)).thenReturn(new ProductDTO[]{p1,p2});

        assertEquals(integration.getProducts(), Arrays.asList(p1, p2));
    }

    @Test
    void getRecommendationsForProductSuccess() {
        Mockito.when(restTemplate.getForObject(recommendationServiceUrl + "?product=1", RecommendationDTO[].class)).thenReturn(new RecommendationDTO[]{recommendation1P1, recommendation2P1});

        assertEquals(integration.getRecommendationsForProduct(1L), recommendationDTOListP1);
    }

    @Test
    void getReviewsForProductSuccess() {
        Mockito.when(restTemplate.getForObject(reviewServiceUrl + "?product=1", ReviewDTO[].class)).thenReturn(new ReviewDTO[]{review1P1, review2P1});

        assertEquals(integration.getReviewsForProduct(1L), reviewDTOListP1);
    }

    @Test
    void addProductSuccess() {
        Mockito.when(restTemplate.postForObject(productServiceUrl, p1, ProductDTO.class)).thenReturn(p1);

        assertEquals(integration.addProduct(p1), p1);
    }

    @Test
    void addReviewSuccess() {
        Mockito.when(restTemplate.postForObject(reviewServiceUrl, review1P1, ReviewDTO.class)).thenReturn(review1P1);

        assertEquals(integration.addReview(review1P1), review1P1);
    }

    @Test
    void addRecommendationSuccess() {
        Mockito.when(restTemplate.postForObject(recommendationServiceUrl, recommendation1P1, RecommendationDTO.class)).thenReturn(recommendation1P1);

        assertEquals(integration.addRecommendation(recommendation1P1), recommendation1P1);
    }

    @Test
    void deleteProductSuccess() {
        Mockito.doNothing().when(restTemplate).delete(productServiceUrl + "/1");
        integration.deleteProduct(1L);
        Mockito.verify(restTemplate).delete(productServiceUrl + "/1");
    }

    @Test
    void deleteReviewSuccess() {
        Mockito.doNothing().when(restTemplate).delete(reviewServiceUrl + "/1");
        integration.deleteReview(1L);
        Mockito.verify(restTemplate).delete(reviewServiceUrl + "/1");
    }

    @Test
    void deleteRecommendationSuccess() {
        Mockito.doNothing().when(restTemplate).delete(recommendationServiceUrl + "/1");
        integration.deleteRecommendation(1L);
        Mockito.verify(restTemplate).delete(recommendationServiceUrl + "/1");
    }

    @Test
    void updateProductSuccess() {
        Mockito.doNothing().when(restTemplate).put(productServiceUrl + "/1", p2);
        Mockito.when(restTemplate.getForObject(productServiceUrl + "/1", ProductDTO.class)).thenReturn(p2);

        assertEquals(integration.updateProduct(p2, 1L), p2);
    }

    @Test
    void updateReviewSuccess() {
        Mockito.doNothing().when(restTemplate).put(reviewServiceUrl + "/1", review2P2);
        Mockito.when(restTemplate.getForObject(reviewServiceUrl + "/1", ReviewDTO.class)).thenReturn(review2P2);

        assertEquals(integration.updateReview(review2P2, 1L), review2P2);
    }

    @Test
    void updateRecommendationSuccess() {
        Mockito.doNothing().when(restTemplate).put(recommendationServiceUrl + "/1", recommendation2P2);
        Mockito.when(restTemplate.getForObject(recommendationServiceUrl + "/1", RecommendationDTO.class)).thenReturn(recommendation2P2);

        assertEquals(integration.updateRecommendation(recommendation2P2, 1L), recommendation2P2);
    }
}
