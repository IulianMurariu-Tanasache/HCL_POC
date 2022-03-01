package hcl.poc.reviewService.service;

import hcl.com.util.NotFoundException;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.api.review.ReviewDTO;
import hcl.poc.reviewService.model.Review;
import hcl.poc.reviewService.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public void modifyReview(Long id, ReviewDTO newReview){
        Review review = repository.getById(id);

        review.setReview_id(id);
        review.setProduct_id(newReview.getProduct_id());
        review.setAuthor(newReview.getAuthor());
        review.setSubject(newReview.getSubject());
        review.setContent(newReview.getContent());

        repository.save(review);
    }

    public ReviewDTO getOneReview(Long id){
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new),ReviewDTO.class);
    }

    public List<ReviewDTO> getAllReviews(){
        List<ReviewDTO> listDTO = new ArrayList<>();
        List<Review> listReview = repository.findAll();

        for(Review review : listReview){
            listDTO.add(modelMapper.map(review, ReviewDTO.class));
        }

        return listDTO;
    }

    public void deleteOneReview(Long id){
        repository.deleteById(id);
    }

    public void addOneReview(ReviewDTO reviewDTO){
        repository.save(modelMapper.map(reviewDTO,Review.class));
    }
}
