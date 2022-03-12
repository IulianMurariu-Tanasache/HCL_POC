package hcl.poc.productcompositeservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import hcl.poc.api.product.ProductDTO;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            @Value("${app.product-service.host}") String productServiceHost,
            @Value("${app.product-service.port}") int productServicePort,
            @Value("${app.recommendation-service.host}") String recommendationServiceHost,
            @Value("${app.recommendation-service.port}") int recommendationServicePort,
            @Value("${app.review-service.host}") String reviewServiceHost,
            @Value("${app.review-service.port}") int reviewServicePort) {

        this.restTemplate = restTemplate;
        this.mapper = mapper;

        LOG.info("{}:{}", productServiceHost, productServicePort);

        productServiceUrl = "http://" + productServiceHost + ":" + productServicePort + "/product";
        recommendationServiceUrl = "http://" + recommendationServiceHost + ":" + recommendationServicePort + "/recommendation";
        reviewServiceUrl = "http://" + reviewServiceHost + ":" + reviewServicePort + "/review";
    }

    public ProductDTO getProduct(Long productId) {

        try {
            String url = productServiceUrl + "/" + productId;
            LOG.debug("Will call getProduct API on URL: {}", url);

            ProductDTO product = restTemplate.getForObject(url, ProductDTO.class);
            if (product != null) {
                LOG.debug("Found a product with id: {}", product.getId());
            }

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

    public List<ProductDTO> getProducts(){
        try {
            List<ProductDTO> productDTOS;

            String url = productServiceUrl;
            LOG.debug("Will call getProducts API on URL: {}", url);

            productDTOS = Arrays.asList(restTemplate.getForObject(url, ProductDTO[].class));

            return productDTOS;
        } catch (Exception e){
            LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", 0);
            return new ArrayList<>();
        }
    }

//    private String getErrorMessage(HttpClientErrorException ex) {
//        try {
//            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
//        } catch (IOException ioex) {
//            return ex.getMessage();
//        }
//    }

    public List<RecommendationDTO> getRecommendationsForProduct(Long productId) {

        try {
            String url = recommendationServiceUrl + "?product=" + productId;

            LOG.debug("Will call getRecommendations API on URL: {}", url);
            List<RecommendationDTO> recommendations = Arrays.asList(restTemplate.getForObject(recommendationServiceUrl + "?product=" + productId, RecommendationDTO[].class));

            LOG.debug("Found {} recommendations for a product with id: {}", recommendations.size(), productId);
            return recommendations;

        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting recommendations, return zero recommendations: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    public List<ReviewDTO> getReviewsForProduct(Long productId) {

        try {
            String url = reviewServiceUrl + "?product=" + productId;

            LOG.debug("Will call getReviews API on URL: {}", url);
            List<ReviewDTO> reviews = Arrays.asList(restTemplate.getForObject(reviewServiceUrl + "?product=" + productId, ReviewDTO[].class));

            LOG.debug("Found {} reviews for a product with id: {}", reviews.size(), productId);
            return reviews;

        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting reviews, return zero reviews: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        try {
            String url = productServiceUrl;

            LOG.debug("Will call postProduct API on URL: {}", url);
            return restTemplate.postForObject(url, productDTO, ProductDTO.class);

        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting reviews, return zero reviews: {}", ex.getMessage());
            throw ex;
        }
    }

    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        try {
            String url = reviewServiceUrl;

            LOG.debug("Will call postReview API on URL: {}", url);
            return restTemplate.postForObject(url, reviewDTO, ReviewDTO.class);

        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting reviews, return zero reviews: {}", ex.getMessage());
            throw ex;
        }
    }

    public RecommendationDTO addRecommendation(RecommendationDTO recommendationDTO) {
        try {
            String url = recommendationServiceUrl;

            LOG.debug("Will call postRecommendation API on URL: {}", url);
            return restTemplate.postForObject(url, recommendationDTO, RecommendationDTO.class);

        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting reviews, return zero reviews: {}", ex.getMessage());
            throw ex;
        }
    }

    public RecommendationDTO addRecommendation(RecommendationDTO recommendationDTO, Long id) {
        recommendationDTO.setProduct_id(id);
        return addRecommendation(recommendationDTO);
    }

    public ReviewDTO addReview(ReviewDTO reviewDTO, Long id) {
        reviewDTO.setProduct_id(id);
        return addReview(reviewDTO);
    }

    public void deleteProduct(Long id){
        restTemplate.delete(productServiceUrl + "/" + id);
    }

    public void deleteRecommendation(Long id){
        restTemplate.delete(recommendationServiceUrl + "/" + id);
    }

    public void deleteReview(Long id){
        restTemplate.delete(reviewServiceUrl + "/" + id);
    }

    public ProductDTO updateProduct(ProductDTO productDTO, Long id){
        restTemplate.put(productServiceUrl + "/" + id, productDTO);
        return restTemplate.getForObject(productServiceUrl + "/" + id, ProductDTO.class);
    }

    public ReviewDTO updateReview(ReviewDTO reviewDTO, Long id){
        restTemplate.put(reviewServiceUrl + "/" + id, reviewDTO);
        return restTemplate.getForObject(reviewServiceUrl + "/" + id, ReviewDTO.class);
    }

    public RecommendationDTO updateRecommendation(RecommendationDTO recommendationDTO, Long id){
        restTemplate.put(recommendationServiceUrl + "/" + id, recommendationDTO);
        return restTemplate.getForObject(recommendationServiceUrl + "/" + id, RecommendationDTO.class);
    }

    public RecommendationDTO updateRecommendation(RecommendationDTO recommendationDTO, Long recommendation_id, Long id) {
        recommendationDTO.setProduct_id(id);
        return updateRecommendation(recommendationDTO, recommendation_id);
    }

    public ReviewDTO updateReview(ReviewDTO reviewDTO, Long review_id, Long id) {
        reviewDTO.setProduct_id(id);
        return updateReview(reviewDTO, review_id);
    }
}
