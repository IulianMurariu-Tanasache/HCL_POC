import hcl.poc.api.recommendation.RecommendationDTO;
import hcl.poc.recommendationservice.controller.RecommendationController;
import hcl.poc.recommendationservice.service.RecommendationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(RecommendationController.class)
@ContextConfiguration(classes = {hcl.poc.recommendationservice.RecommendationServiceApplication.class})
public class RecommendationControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private RecommendationService service;

    private RecommendationDTO recommendation1 = new RecommendationDTO(1L, 1L, "First Author", 3.75, "This is the content");
    private RecommendationDTO recommendation2 = new RecommendationDTO(1L, 2L, "Second Author", 4.75, "This is the content");
    private RecommendationDTO recommendation3 = new RecommendationDTO(2L, 3L, "Third Author", 4.11, "This is the content");

    @Test
    void getRecommendationByIDSuccess() {

        Mockito.when(service.getOneRecommendation(recommendation1.getRecommendation_id())).thenReturn(recommendation1);

        webClient.get()
                .uri("/recommendation/1")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecommendationDTO.class).isEqualTo(recommendation1);
    }

    @Test
    void getAllRecommendationsSuccess(){
        List<RecommendationDTO> recommendationDTOList = new ArrayList(Arrays.asList(recommendation1,recommendation2,recommendation3));

        Mockito.when(service.getAllRecommendations()).thenReturn(recommendationDTOList);

        webClient.get()
                .uri("/recommendation")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecommendationDTO.class).isEqualTo(recommendationDTOList);
    }

    @Test
    void getAllRecommendationsForProductSuccess(){
        List<RecommendationDTO> recommendationDTOList = new ArrayList(Arrays.asList(recommendation1,recommendation2));

        Mockito.when(service.getAllRecommendationsForProduct(1L)).thenReturn(recommendationDTOList);

        webClient.get()
                .uri("/recommendation?product=1")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecommendationDTO.class).isEqualTo(recommendationDTOList);
    }

    @Test
    void postRecommendationSuccess(){
        Mockito.when(service.addOneRecommendation(recommendation2)).thenReturn(recommendation2);

        webClient.post()
                .uri("/recommendation")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recommendation2)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecommendationDTO.class).isEqualTo(recommendation2);

        Mockito.verify(service).addOneRecommendation(recommendation2);
    }

    @Test
    void putRecommendationSuccess() {
        Mockito.when(service.modifyRecommendation(recommendation1.getRecommendation_id(), recommendation3)).thenReturn(recommendation3);

        webClient.put()
                .uri("/recommendation/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recommendation3)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecommendationDTO.class).isEqualTo(recommendation3);

        Mockito.verify(service).modifyRecommendation(recommendation1.getRecommendation_id(), recommendation3);
    }

    @Test
    void deleteRecommendationSuccess() {
        Mockito.doNothing().when(service).deleteOneRecommendation(recommendation2.getRecommendation_id());

        webClient.delete()
                .uri("/recommendation/2")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(service).deleteOneRecommendation(recommendation2.getRecommendation_id());
    }


}
