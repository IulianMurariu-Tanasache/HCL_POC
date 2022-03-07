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
        productAggregate.setRecommendations(recommendations);
        productAggregate.setReviews(reviews);
        LOG.info("returning productAggregate");
        return productAggregate;
    }

    public ProductAggregate getProductComposite(Long id){
        LOG.info("getProductComposite");
        return createProductAggregate(integration.getProduct(id), integration.getReviews(id), integration.getRecommendations(id));
    }

    public List<ProductAggregate> getAllProductComposite(){
        List<ProductAggregate> productAggregates = new ArrayList<>();

        for(ProductDTO product : integration.getProducts()) {
            productAggregates.add(createProductAggregate(product, integration.getReviews(product.getId()), integration.getRecommendations(product.getId())));
        }

        return productAggregates;
    }

    public ProductAggregate addProductComposite(ProductAggregate productAggregate){

        ProductDTO productDTO = new ProductDTO(productAggregate.getId(), productAggregate.getName(), productAggregate.getWeight());

        integration.addProduct(productDTO);
        for(RecommendationDTO recommendationDTO : productAggregate.getRecommendations()) {
            integration.addRecommendation(recommendationDTO);
        }
        for(ReviewDTO reviewDTO : productAggregate.getReviews()) {
            integration.addReview(reviewDTO);
        }
        return productAggregate;
    }

    public void updateProductComposite(){

    }

    public void deleteProductComposite(){

    }
}
