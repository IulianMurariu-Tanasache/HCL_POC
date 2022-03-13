package hcl.poc.productcompositeservice.service;

import hcl.poc.api.product.ProductDTO;
import hcl.poc.api.productcomposite.ProductAggregate;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;
import hcl.poc.productcompositeservice.integration.ProductCompositeIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCompositeService {

    @Autowired
    private ProductCompositeIntegration integration;

    private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeService.class);

    public ProductAggregate createProductAggregate(ProductDTO product, List<ReviewDTO> reviews, List<RecommendationDTO> recommendations)
    {
        ProductAggregate productAggregate = new ProductAggregate();
        productAggregate.setId(product.getId());
        productAggregate.setName(product.getName());
        productAggregate.setWeight(product.getWeight());
        productAggregate.setRecommendationList(recommendations);
        productAggregate.setReviewList(reviews);
        LOG.info("returning productAggregate");
        return productAggregate;
    }

    public ProductAggregate getProductComposite(Long id){
        LOG.info("getProductComposite");
        return createProductAggregate(integration.getProduct(id), integration.getReviewsForProduct(id), integration.getRecommendationsForProduct(id));
    }

    public List<ProductAggregate> getAllProductComposite(){
        List<ProductAggregate> productAggregates = new ArrayList<>();

        for(ProductDTO product : integration.getProducts()) {
            productAggregates.add(createProductAggregate(product, integration.getReviewsForProduct(product.getId()), integration.getRecommendationsForProduct(product.getId())));
        }

        return productAggregates;
    }

    public ProductAggregate addProductComposite(ProductAggregate productAggregate){

        ProductDTO productDTO = new ProductDTO(productAggregate.getId(), productAggregate.getName(), productAggregate.getWeight());

        ProductDTO product = integration.addProduct(productDTO);
        List<RecommendationDTO> recommendationDTOS = new ArrayList<>();
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        System.out.println(productAggregate);
        for(RecommendationDTO recommendationDTO : productAggregate.getRecommendationList()) {
            recommendationDTOS.add(integration.addRecommendation(recommendationDTO, product.getId()));
        }
        for(ReviewDTO reviewDTO : productAggregate.getReviewList()) {
            reviewDTOs.add(integration.addReview(reviewDTO, product.getId()));
        }

        return createProductAggregate(product, reviewDTOs, recommendationDTOS);
    }

    public ProductAggregate updateProductComposite(Long id, ProductAggregate productAggregate){
        //TODO make this either add new product, reviews, recommendations or update existing one -> also do this in integration
        //TODO remake tests for updates for integration and service and controller
        ProductDTO newProductDTO = new ProductDTO(productAggregate.getId(), productAggregate.getName(), productAggregate.getWeight());
        ProductDTO product = integration.updateProduct(newProductDTO, id);

        List<RecommendationDTO> recommendationDTOS = new ArrayList<>();
        List<ReviewDTO> reviewDTOs = new ArrayList<>();

        for(RecommendationDTO recommendationDTO : integration.getRecommendationsForProduct(id)) {
            recommendationDTOS.add(integration.updateRecommendation(recommendationDTO, recommendationDTO.getRecommendationId(), newProductDTO.getId()));
        }
        for(ReviewDTO reviewDTO : integration.getReviewsForProduct(id)) {
            reviewDTOs.add(integration.updateReview(reviewDTO, reviewDTO.getReviewId(), newProductDTO.getId()));
        }

        return createProductAggregate(product, reviewDTOs, recommendationDTOS);
    }

    public void deleteProductComposite(Long id){

        integration.deleteProduct(id);

        for(RecommendationDTO recommendationDTO : integration.getRecommendationsForProduct(id))
            integration.deleteRecommendation(recommendationDTO.getRecommendationId());

        for(ReviewDTO reviewDTO : integration.getReviewsForProduct(id))
            integration.deleteReview(reviewDTO.getReviewId());
    }
}
