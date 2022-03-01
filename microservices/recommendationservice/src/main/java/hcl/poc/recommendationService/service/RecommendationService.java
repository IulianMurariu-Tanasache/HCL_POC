package hcl.poc.recommendationService.service;

import hcl.com.util.NotFoundException;
import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.recommendationService.model.Recommendation;
import hcl.poc.recommendationService.repository.RecommendationRepository;
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

    public void modifyRecommendation(Long id, RecommendationDTO newRecommendation){
        Recommendation recommendation = repository.getById(id);

        recommendation.setRecommendation_id(id);
        recommendation.setAuthor(newRecommendation.getAuthor());
        recommendation.setContent(newRecommendation.getContent());
        recommendation.setProduct_id(newRecommendation.getProduct_id());
        recommendation.setRate(newRecommendation.getRate());

        repository.save(recommendation);
    }

    public RecommendationDTO getOneRecommendation(Long id){
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new),RecommendationDTO.class);
    }

    public List<RecommendationDTO> getAllUser(){
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

    public void addOneRecommendation(RecommendationDTO recommendationDTO){
        repository.save(modelMapper.map(recommendationDTO,Recommendation.class));
    }
}
