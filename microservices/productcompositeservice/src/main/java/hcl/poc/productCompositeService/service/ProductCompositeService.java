package hcl.poc.productCompositeService.service;

import hcl.poc.api.product.ProductDTO;
import hcl.poc.api.productcomposite.ProductAggregate;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;
import hcl.poc.productCompositeService.integration.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    public void addProductComposite(){

    }

    public void updateProductComposite(){

    }

    public void deleteProductComposite(){

    }
}
