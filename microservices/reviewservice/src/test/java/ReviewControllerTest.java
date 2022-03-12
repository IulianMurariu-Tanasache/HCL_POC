import hcl.poc.api.review.ReviewDTO;
import hcl.poc.reviewservice.controller.ReviewController;
import hcl.poc.reviewservice.service.ReviewService;
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
@WebFluxTest(ReviewController.class)
@ContextConfiguration(classes = {hcl.poc.reviewservice.ReviewServiceApplication.class})
public class ReviewControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ReviewService service;

    private ReviewDTO review1 = new ReviewDTO(1L, 1L, "First Author", "First Subject", "The content");
    private ReviewDTO review2 = new ReviewDTO(2L, 1L, "Second Author", "Second Subject", "The content");
    private ReviewDTO review3 = new ReviewDTO(3L, 2L, "Third Author", "Third Subject", "The content");

    @Test
    void getReviewByIDSuccess() {
        Mockito.when(service.getOneReview(review1.getReview_id())).thenReturn(review1);

        webClient.get()
                .uri("/review/1")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReviewDTO.class).isEqualTo(review1);
    }

    @Test
    void getAllReviewsSuccess() {
        List<ReviewDTO> reviewDTOList = new ArrayList(Arrays.asList(review1, review2, review3));

        Mockito.when(service.getAllReviews()).thenReturn(reviewDTOList);

        webClient.get()
                .uri("/review")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange().expectStatus().isOk()
                .expectBodyList(ReviewDTO.class).isEqualTo(reviewDTOList);
    }

    @Test
    void getAllReviewsForProductSuccess() {
        List<ReviewDTO> reviewDTOList = new ArrayList(Arrays.asList(review1, review2));

        Mockito.when(service.getAllReviewsForProduct(1L)).thenReturn(reviewDTOList);

        webClient.get()
                .uri("/review?product=1")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange().expectStatus().isOk()
                .expectBodyList(ReviewDTO.class).isEqualTo(reviewDTOList);
    }

    @Test
    void postReviewSuccess() {
        Mockito.when(service.addOneReview(review2)).thenReturn(review2);

        webClient.post()
                .uri("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(review2)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReviewDTO.class).isEqualTo(review2);

        Mockito.verify(service).addOneReview(review2);
    }

    @Test
    void putReviewSuccess() {
        Mockito.when(service.modifyReview(review1.getReview_id(), review3)).thenReturn(review3);

        webClient.put()
                .uri("/review/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(review3)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReviewDTO.class).isEqualTo(review3);

        Mockito.verify(service).modifyReview(review1.getReview_id(), review3);
    }

    @Test
    void deleteReviewSuccess() {
        Mockito.doNothing().when(service).deleteOneReview(review2.getReview_id());

        webClient.delete()
                .uri("/review/2")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(service).deleteOneReview(review2.getReview_id());
    }
}
