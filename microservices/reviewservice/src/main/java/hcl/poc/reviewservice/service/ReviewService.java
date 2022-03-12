package hcl.poc.reviewservice.service;

import hcl.com.util.NotFoundException;
import hcl.poc.api.review.ReviewDTO;
import hcl.poc.reviewservice.model.Review;
import hcl.poc.reviewservice.repository.ReviewRepository;
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

    public ReviewDTO modifyReview(Long id, ReviewDTO newReview){
        Review review = repository.getById(id);

        review.setReview_id(id);
        review.setProduct_id(newReview.getProduct_id());
        review.setAuthor(newReview.getAuthor());
        review.setSubject(newReview.getSubject());
        review.setContent(newReview.getContent());

        repository.save(review);
        return modelMapper.map(review, ReviewDTO.class);
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

    public ReviewDTO addOneReview(ReviewDTO reviewDTO){
        repository.save(modelMapper.map(reviewDTO,Review.class));
        return reviewDTO;
    }

    public List<ReviewDTO> getAllReviewsForProduct(Long id) {
        List<Review> reviewList = repository.findAllByProductId(id);
        List<ReviewDTO> listDTO = new ArrayList<>();

        for(Review review : reviewList){
            listDTO.add(modelMapper.map(review, ReviewDTO.class));
        }

        return listDTO;
    }
}
