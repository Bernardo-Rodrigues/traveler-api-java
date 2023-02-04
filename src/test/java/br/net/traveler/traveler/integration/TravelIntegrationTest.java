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
public class TravelIntegrationTest implements WithAssertions {

    private static final String TRAVEL_CONTROLLER_BASE_URL = "/travels";
    private static final String CURRENT_TRIP_BASE_URL = TRAVEL_CONTROLLER_BASE_URL + "/current";

    @Autowired
    private MockMvc mvc;
    private SeedTest seed = new SeedTest();


    @Test
    void givenAFindCurrentTripRequestWhenTheUserAndTripExistsThenReturnThisTrip() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(CURRENT_TRIP_BASE_URL)
                                .header("user-id", 1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("First Destination", "startDate", "endDate");
    }

}
