package br.net.traveler.traveler.integration;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.Review;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.mother.DestinationMother;
import br.net.traveler.traveler.domain.mother.ReviewMother;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.domain.request.AddReviewRequest;
import br.net.traveler.traveler.repositories.ReviewRepository;
import br.net.traveler.traveler.services.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class ReviewIntegrationTest implements WithAssertions {

    private static final String REVIEW_CONTROLLER_BASE_URL = "/reviews";
    private static final String ADD_REVIEW_TO_DESTINATION_URL = REVIEW_CONTROLLER_BASE_URL + "/destinations";
    private String JWT = "";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeAll
    void setJWT(){
        JWT = jwtService.generateToken(UserDto.builder().id("id1").build());
    }

    @Test
    void givenAListAchievementsRequestWhenTheUSerExistsThenReturnAllAchievementsOfTheUser() throws Exception {
        AddReviewRequest request = ReviewMother.getAddReviewRequest();
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();
        destination.setId(2);
        
        MockHttpServletResponse response = mvc.perform(
                        post(ADD_REVIEW_TO_DESTINATION_URL + "/" + destination.getId())
                                .header("jwt", JWT)
                                .content(new ObjectMapper().writeValueAsString(request).getBytes())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        Review review = reviewRepository.findByUserIdAndDestinationId(user.getId(), destination.getId());

        assertThat(review).isNotNull();
        assertThat(review.getNote()).isEqualTo(request.getNote());
    }

}
