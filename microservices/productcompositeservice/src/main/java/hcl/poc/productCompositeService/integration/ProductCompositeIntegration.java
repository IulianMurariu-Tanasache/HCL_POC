package hcl.poc.productCompositeService.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import hcl.poc.api.product.ProductDTO;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Component
public class ProductCompositeIntegration {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeIntegration.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String productServiceUrl;
    private final String recommendationServiceUrl;
    private final String reviewServiceUrl;

    @Autowired
    public ProductCompositeIntegration(
            RestTemplate restTemplate,
            ObjectMapper mapper,
            @Value("localhost") String productServiceHost,
            @Value("7001") int productServicePort,
            @Value("localhost") String recommendationServiceHost,
            @Value("7003") int recommendationServicePort,
            @Value("localhost") String reviewServiceHost,
            @Value("7002") int reviewServicePort) {

        this.restTemplate = restTemplate;
        this.mapper = mapper;

        LOG.info(productServiceHost + ":" + productServicePort);

        productServiceUrl = "http://" + productServiceHost + ":" + productServicePort + "/product/";
        recommendationServiceUrl = "http://" + recommendationServiceHost + ":" + recommendationServicePort + "/recommendation?productId=";
        reviewServiceUrl = "http://" + reviewServiceHost + ":" + reviewServicePort + "/review?productId=";
    }

    public ProductDTO getProduct(Long productId) {

        try {
            String url = productServiceUrl + productId;
            LOG.debug("Will call getProduct API on URL: {}", url);

            ProductDTO product = restTemplate.getForObject(url, ProductDTO.class);
            LOG.debug("Found a product with id: {}", product.getId());

            return product;

        } catch (HttpClientErrorException ex) {

            switch (ex.getStatusCode()) {
//                case NOT_FOUND:
//                    throw new NotFoundException(getErrorMessage(ex));
//
//                case UNPROCESSABLE_ENTITY:
//                    throw new InvalidInputException(getErrorMessage(ex));

                default:
                    LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                    LOG.warn("Error body: {}", ex.getResponseBodyAsString());
                    throw ex;
            }
        }
    }

//    private String getErrorMessage(HttpClientErrorException ex) {
//        try {
//            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
//        } catch (IOException ioex) {
//            return ex.getMessage();
//        }
//    }

    public List<RecommendationDTO> getRecommendations(Long productId) {

        try {
            String url = recommendationServiceUrl + productId;

            LOG.debug("Will call getRecommendations API on URL: {}", url);
            List<RecommendationDTO> recommendations = restTemplate
                    .exchange(url, GET, null, new ParameterizedTypeReference<List<RecommendationDTO>>() {})
                    .getBody();

            LOG.debug("Found {} recommendations for a product with id: {}", recommendations.size(), productId);
            return recommendations;

        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting recommendations, return zero recommendations: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    public List<ReviewDTO> getReviews(Long productId) {

        try {
            String url = reviewServiceUrl + productId;

            LOG.debug("Will call getReviews API on URL: {}", url);
            List<ReviewDTO> reviews = restTemplate
                    .exchange(url, GET, null, new ParameterizedTypeReference<List<ReviewDTO>>() {})
                    .getBody();

            LOG.debug("Found {} reviews for a product with id: {}", reviews.size(), productId);
            return reviews;

        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting reviews, return zero reviews: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

}
