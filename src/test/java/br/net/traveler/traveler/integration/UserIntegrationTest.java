package br.net.traveler.traveler.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.domain.request.UserDeletionRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import br.net.traveler.traveler.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserIntegrationTest implements WithAssertions {

    private static final String USER_CONTROLLER_BASE_URL = "/user-events";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    void givenANewUserWhenInformationAreOkThenCreateTheUser() throws Exception {
        UserRegistrationRequest request = UserMother.getUserRegistrationRequest();

        mvc.perform(
           post(USER_CONTROLLER_BASE_URL)
             .content(new ObjectMapper().writeValueAsString(request).getBytes())
             .accept(MediaType.APPLICATION_JSON)
             .contentType(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(status().isCreated())
           .andReturn().getResponse();

        assertThat(userRepository.findById(request.getId())).isNotNull();
    }

    @Test
    void givenACorrectIdWhenDeletingAnUserThenDeleteTheUser() throws Exception {
        UserDeletionRequest request = UserMother.getUserDeletionRequest();

        mvc.perform(
            delete(USER_CONTROLLER_BASE_URL)
                .content(new ObjectMapper().writeValueAsString(request).getBytes())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNoContent())
            .andReturn().getResponse();

        assertThat(userRepository.findById(request.getId())).isEmpty();
    }
}
