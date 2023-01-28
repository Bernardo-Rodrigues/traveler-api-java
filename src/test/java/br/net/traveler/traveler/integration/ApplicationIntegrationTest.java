package br.net.traveler.traveler.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.net.traveler.traveler.config.SeedTest;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.entities.pk.ReviewsPk;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.mother.DestinationMother;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.domain.request.UserAuthenticationRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import br.net.traveler.traveler.domain.request.UserUpdateRequest;
import br.net.traveler.traveler.repositories.*;
import br.net.traveler.traveler.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationIntegrationTest implements WithAssertions {

    private static final String USER_CONTROLLER_BASE_URL = "/users";
    private static final String DESTINATION_CONTROLLER_BASE_URL = "/destinations";
    private static final String DESTINATION_URL = DESTINATION_CONTROLLER_BASE_URL + "/1";

    private static final String FAVORITE_DESTINATION_URL = DESTINATION_URL + "/favorite";
    private static final String UNFAVORITE_DESTINATION_URL = DESTINATION_URL + "/unfavorite";
    private static final String AUTHENTICATE_USER_URL = USER_CONTROLLER_BASE_URL + "/authenticate";
    private static final String TOP_DESTINATIONS_URL = DESTINATION_CONTROLLER_BASE_URL + "/top";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserService userService;
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
    private UserRepository userRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserMapper userMapper;
    private SeedTest seed = new SeedTest();

    @Test
    void givenANewUserWhenInformationAreOkThenCreateTheUser() throws Exception {
        UserRegistrationRequest request = UserMother.getUserRegistrationRequest();
        UserDto user = userMapper.registrationRequestToDto(request);

        MockHttpServletResponse response = mvc.perform(
           post(USER_CONTROLLER_BASE_URL)
             .content(new ObjectMapper().writeValueAsString(request).getBytes())
             .accept(MediaType.APPLICATION_JSON)
             .contentType(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(status().isCreated())
           .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("uri");
        assertThat(userRepository.findByEmail(user.getEmail())).isNotNull();
    }

    @Test
    void givenACorrectIdWhenSearchingForAnUserThenReturnTheUser() throws Exception {
        User user = userRepository.findById(1).get();

        MockHttpServletResponse response = mvc.perform(
                        get(USER_CONTROLLER_BASE_URL + "/" + user.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains(user.getId().toString(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    @Test
    void givenUserInformationWhenTheTheUserAlreadyExistsThenUpdateItsInformation() throws Exception {
        User createdUser = userRepository.findById(1).get();
        UserUpdateRequest request = UserMother.getUserUpdateRequest();

        MockHttpServletResponse response = mvc.perform(
                        put(USER_CONTROLLER_BASE_URL + "/" + createdUser.getId())
                                .content(new ObjectMapper().writeValueAsString(request).getBytes())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("uri");
        assertThatNoException().isThrownBy(() -> userRepository.findByUsername(request.getUsername()));
    }

    @Test
    void givenAnAuthenticationAttemptWhenInformationAreOkThenReturnAJwt() throws Exception {
        UserAuthenticationRequest request = UserMother.getUserAuthenticationRequest();

        MockHttpServletResponse response = mvc.perform(
           post(AUTHENTICATE_USER_URL)
                   .content(new ObjectMapper().writeValueAsString(request).getBytes())
                   .accept(MediaType.APPLICATION_JSON)
                   .contentType(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(status().isOk())
           .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("jwt");
    }

    @Test
    void givenAListDestinationsRequestWhenThereIsNoNameParameterThenReturnAllDestinations() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(DESTINATION_CONTROLLER_BASE_URL)
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
                                .accept(MediaType.APPLICATION_JSON)
                                .header("user-id", 1)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andReturn().getResponse();

        assertThat(favoriteRepository.findByUserIdAndDestinationId(1, 1)).isNotNull();
    }

    @Test
    void givenADestinationWhenUserWantsToUnfavoriteItThenRemoveFromTheDatabase() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        post(UNFAVORITE_DESTINATION_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("user-id", 1)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andReturn().getResponse();

        assertThat(favoriteRepository.findByUserIdAndDestinationId(1, 1)).isNull();
    }

    @Test
    void givenAnAttemptToFindADestinationWhenTheUserAndDestinationsExitsThenReturnItWithExtraInformation() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(DESTINATION_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("user-id", 1)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("First Destination", "visited", "favorited", "score", "personalNote");
    }
}
