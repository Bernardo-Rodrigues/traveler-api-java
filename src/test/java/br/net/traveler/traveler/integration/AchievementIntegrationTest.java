package br.net.traveler.traveler.integration;

import br.net.traveler.traveler.config.SeedTest;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AchievementIntegrationTest implements WithAssertions {

    private static final String ACHIEVEMENT_CONTROLLER_BASE_URL = "/achievements";

    @Autowired
    private MockMvc mvc;
    private SeedTest seed = new SeedTest();


    @Test
    void givenAListAchievementsRequestWhenTheUSerExistsThenReturnAllAchievementsOfTheUser() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(ACHIEVEMENT_CONTROLLER_BASE_URL)
                                .header("user-id", 1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("First Achievement", "Second Achievement");
    }

}
