package hcl.poc.reviewservice;

import hcl.poc.api.product.ProductDTO;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;
import hcl.poc.reviewservice.model.Review;
import hcl.poc.reviewservice.service.ReviewService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@OpenAPIDefinition
@SpringBootApplication
@EntityScan(basePackages = {"hcl.poc.reviewservice.model"} )
@EnableJpaRepositories(basePackages = {"hcl.poc.reviewservice.repository"})
public class ReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
