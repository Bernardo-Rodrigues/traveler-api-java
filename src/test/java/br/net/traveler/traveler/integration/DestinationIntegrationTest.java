package br.net.traveler.traveler.integration;

import br.net.traveler.traveler.config.SeedTest;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.domain.request.UserAuthenticationRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import br.net.traveler.traveler.domain.request.UserUpdateRequest;
import br.net.traveler.traveler.repositories.*;
import br.net.traveler.traveler.services.JwtService;
import br.net.traveler.traveler.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class DestinationIntegrationTest implements WithAssertions {

    private static final String DESTINATION_CONTROLLER_BASE_URL = "/destinations";
    private static final String FAVORITE_DESTINATIONS_URL = DESTINATION_CONTROLLER_BASE_URL + "/favorites";
    private static final String TOP_DESTINATIONS_URL = DESTINATION_CONTROLLER_BASE_URL + "/top";
    private static final String DESTINATION_URL = DESTINATION_CONTROLLER_BASE_URL + "/1";
    private static final String FAVORITE_DESTINATION_URL = DESTINATION_URL + "/favorite";
    private static final String UNFAVORITE_DESTINATION_URL = DESTINATION_URL + "/unfavorite";
    private static final String DESTINATION_TIPS_URL = DESTINATION_URL + "/tips";
    private String JWT = "";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private LocalizationRepository localizationRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ContinentRepository continentRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;

    @BeforeAll
    void setJWT(){
        JWT = jwtService.generateToken(UserDto.builder().username("user 1").build());
    }

    @Test
    void givenAListDestinationsRequestWhenThereIsNoNameParameterThenReturnAllDestinations() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(DESTINATION_CONTROLLER_BASE_URL)
                                .header("jwt", JWT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("First Destination", "Second Destination");
    }

    @Test
    void givenAListDestinationsRequestWhenThereIsNameParameterThenReturnAllDestinationsThatStartsWithThatName() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(DESTINATION_CONTROLLER_BASE_URL + "?name=First")
                                .header("jwt", JWT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("First Destination");
        assertThat(response.getContentAsString()).doesNotContain("Second Destination");
    }

    @Test
    void givenAListTopDestinationsRequestWhenDestinationsHaveReviewsThenReturnAllDestinationsOrderedByScore() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(TOP_DESTINATIONS_URL)
                                .header("jwt", JWT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("score");
        assertThat(response.getContentAsString().indexOf("4.0")).isLessThan(response.getContentAsString().indexOf("3.0"));
    }

    @Test
    void givenADestinationWhenUserWantsToFavoriteItThenAddToTheDatabase() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        post(FAVORITE_DESTINATION_URL)
                                .header("jwt", JWT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andReturn().getResponse();

        assertThat(favoriteRepository.findByUserIdAndDestinationId(1, 1)).isNotNull();
    }

    @Test
    void givenADestinationWhenUserWantsToUnfavoriteItThenRemoveFromTheDatabase() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        post(UNFAVORITE_DESTINATION_URL)
                                .header("jwt", JWT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andReturn().getResponse();

        assertThat(favoriteRepository.findByUserIdAndDestinationId(1, 1)).isNull();
    }

    @Test
    void givenAnAttemptToFindADestinationWhenTheUserAndDestinationsExistsThenReturnItWithExtraInformation() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(DESTINATION_URL)
                                .header("jwt", JWT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("First Destination", "visited", "favorited", "score", "personalNote");
    }

    @Test
    void givenAnAttemptToListFavoriteDestinationsWhenTheUserExistsThenReturnItsFavoriteDestinationsWithScores() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(FAVORITE_DESTINATIONS_URL)
                                .header("jwt", JWT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("First Destination", "score", "countryName");
    }

    @Test
    void givenAnAttemptToListDestinationTipsWhenTheDestinationExistsThenReturnItsTips() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(DESTINATION_TIPS_URL)
                                .header("jwt", JWT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("First tip", "Second tip");
    }
}
