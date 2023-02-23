package br.net.traveler.traveler.integration;

import br.net.traveler.traveler.config.SeedTest;
import br.net.traveler.traveler.domain.entities.Review;
import br.net.traveler.traveler.domain.entities.Travel;
import br.net.traveler.traveler.domain.mother.ReviewMother;
import br.net.traveler.traveler.domain.mother.TravelMother;
import br.net.traveler.traveler.domain.request.AddReviewRequest;
import br.net.traveler.traveler.domain.request.AddTravelRequest;
import br.net.traveler.traveler.repositories.TravelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelIntegrationTest implements WithAssertions {

    private static final String TRAVEL_CONTROLLER_BASE_URL = "/travels";
    private static final String CURRENT_TRIP_BASE_URL = TRAVEL_CONTROLLER_BASE_URL + "/current";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private TravelRepository travelRepository;

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

        assertThat(response.getContentAsString()).contains("\"destinationId\":2", "startDate", "endDate");
    }
    @Test
    void givenAListUpcomingTripsRequestWhenTheUserExistsAndHasTripsThenReturnTheseTrips() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get(TRAVEL_CONTROLLER_BASE_URL)
                                .header("user-id", 1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("\"destinationId\":1", "\"destinationId\":2");
        assertThat(response.getContentAsString().indexOf("\"destinationId\":2")).isLessThan(response.getContentAsString().indexOf("\"destinationId\":1"));
    }

    @Test
    void givenAPostTravelRequestWhenTheUserAndDestinationAndExistsAndDatesAreValidThenSaveTheTrip() throws Exception {
        AddTravelRequest request = TravelMother.getAddTravelRequest();

        MockHttpServletResponse response = mvc.perform(
                        post(TRAVEL_CONTROLLER_BASE_URL)
                                .header("user-id", 1)
                                .content(new ObjectMapper().writeValueAsString(request).getBytes())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).contains("uri");

        String[] uri = response.getContentAsString().split("/");
        Integer id = Integer.parseInt(uri[uri.length - 1].split("\"")[0]);

        assertThat(travelRepository.findById(id).get()).isNotNull();
    }

}
