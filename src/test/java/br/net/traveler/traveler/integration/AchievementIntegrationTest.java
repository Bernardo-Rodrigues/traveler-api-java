package br.net.traveler.traveler.integration;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.services.JwtService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AchievementIntegrationTest implements WithAssertions {

    private static final String ACHIEVEMENT_CONTROLLER_BASE_URL = "/achievements";
    private static final String GET_ACHIEVEMENT_URL = ACHIEVEMENT_CONTROLLER_BASE_URL + "/destinations" + "/3";
    private String JWT = "";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JwtService jwtService;

    @BeforeAll
    void setJWT(){
        JWT = jwtService.generateToken(UserDto.builder().id("id1").build());
    }

    @Test
    void givenAListAchievementsRequestWhenTheUserExistsThenReturnAllAchievementsOfTheUser() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(ACHIEVEMENT_CONTROLLER_BASE_URL)
                                .header("jwt", JWT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("First Achievement", "Second Achievement");
    }

    @Test
    void givenAGetDestinationAchievementRequestWhenTheDestinationExistsAndTheUserDoesNotHaveTheAchievementYetThenReturnAllAchievementsObtained() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(GET_ACHIEVEMENT_URL)
                                .header("jwt", JWT)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        System.out.println(response.getContentAsString());

        assertThat(response.getContentAsString()).contains("Third Achievement", "Count Achievement");
    }

}
