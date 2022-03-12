package hcl.poc.recommendationservice.service;

import hcl.com.util.NotFoundException;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.recommendationservice.model.Recommendation;
import hcl.poc.recommendationservice.repository.RecommendationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public RecommendationDTO modifyRecommendation(Long id, RecommendationDTO newRecommendation){
        Recommendation recommendation = repository.getById(id);

        recommendation.setRecommendation_id(id);
        recommendation.setAuthor(newRecommendation.getAuthor());
        recommendation.setContent(newRecommendation.getContent());
        recommendation.setProduct_id(newRecommendation.getProduct_id());
        recommendation.setRate(newRecommendation.getRate());

        repository.save(recommendation);

        return modelMapper.map(recommendation, RecommendationDTO.class);
    }

    public RecommendationDTO getOneRecommendation(Long id){
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new),RecommendationDTO.class);
    }

    public List<RecommendationDTO> getAllRecommendations(){
        List<RecommendationDTO> listDTO = new ArrayList<>();
        List<Recommendation> listRecommendation = repository.findAll();

        for(Recommendation recommendation : listRecommendation){
            listDTO.add(modelMapper.map(recommendation, RecommendationDTO.class));
        }

        return listDTO;
    }

    public void deleteOneRecommendation(Long id){
        repository.deleteById(id);
    }

    public RecommendationDTO addOneRecommendation(RecommendationDTO recommendationDTO){
        repository.save(modelMapper.map(recommendationDTO,Recommendation.class));
        return recommendationDTO;
    }

    public List<RecommendationDTO> getAllRecommendationsForProduct(Long id) {
        List<RecommendationDTO> listDTO = new ArrayList<>();
        List<Recommendation> listRecommendation = repository.findAllByProductId(id);

        for(Recommendation recommendation : listRecommendation){
            listDTO.add(modelMapper.map(recommendation, RecommendationDTO.class));
        }

        return listDTO;
    }
}
