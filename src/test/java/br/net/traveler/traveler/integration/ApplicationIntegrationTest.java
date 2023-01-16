package br.net.traveler.traveler.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.domain.request.UserAuthenticationRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import br.net.traveler.traveler.domain.request.UserUpdateRequest;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationIntegrationTest implements WithAssertions {

    private static final String CONTROLLER_BASE_URL = "/users";
    private static final String AUTHENTICATE_USER_URL = CONTROLLER_BASE_URL + "/authenticate";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    public void truncateDatabase(){
        userRepository.deleteAll();
    }

    @Test
    void givenANewUserWhenInformationAreOkThenCreateTheUser() throws Exception {
        UserRegistrationRequest request = UserMother.getUserRegistrationRequest();
        UserDto user = userMapper.registrationRequestToDto(request);

        MockHttpServletResponse response = mvc.perform(
           post(CONTROLLER_BASE_URL)
             .content(new ObjectMapper().writeValueAsString(request).getBytes())
             .accept(MediaType.APPLICATION_JSON)
             .contentType(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(status().isCreated())
           .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("uri");
        assertThatNoException().isThrownBy(() -> userRepository.findByEmail(user.getEmail()));
    }

    @Test
    void givenACorrectIdWhenSearchingForAnUserThenReturnTheUser() throws Exception {
        UserDto user = UserMother.getUserDto();
        user = userService.createUser(user);

        MockHttpServletResponse response = mvc.perform(
                        get(CONTROLLER_BASE_URL + "/" + user.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("id", "username", "email", "password");
    }

    @Test
    void givenUserInformationWhenTheTheUserAlreadyExistsThenUpdateItsInformation() throws Exception {
        UserDto user = UserMother.getUserDto();
        UserDto createdUser = userService.createUser(user);
        UserUpdateRequest request = UserMother.getUserUpdateRequest();

        MockHttpServletResponse response = mvc.perform(
                        put(CONTROLLER_BASE_URL + "/" + createdUser.getId())
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
        UserDto user = userMapper.authenticationRequestToDto(request);
        userService.createUser(user);

        MockHttpServletResponse response = mvc.perform(
           post(AUTHENTICATE_USER_URL)
                   .content(new ObjectMapper().writeValueAsString(request).getBytes())
                   .accept(MediaType.APPLICATION_JSON)
                   .contentType(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(status().isOk())
           .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("jwt");
    }
}
