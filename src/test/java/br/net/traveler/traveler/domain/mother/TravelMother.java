package br.net.traveler.traveler.domain.mother;

import br.net.traveler.traveler.domain.dto.TravelDto;
import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.Review;
import br.net.traveler.traveler.domain.entities.Travel;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.entities.pk.ReviewsPk;
import br.net.traveler.traveler.domain.request.AddTravelRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

public class TravelMother {

    public static AddTravelRequest getAddTravelRequest() {
        return AddTravelRequest.builder()
                .startDate(Date.from(Instant.now().plus(12, ChronoUnit.DAYS)))
                .endDate(Date.from(Instant.now().plus(14, ChronoUnit.DAYS)))
                .destinationId(1)
                .build();
    }

    public static TravelDto getTravelDto() {
        return TravelDto.builder()
                .startDate(Date.from(Instant.now()))
                .endDate(Date.from(Instant.now().plus(5, ChronoUnit.DAYS)))
                .destinationId(1)
                .userId(1)
                .build();
    }

    public static Travel getTravel() {
        return Travel.builder()
                .startDate(Date.from(Instant.now()))
                .endDate(Date.from(Instant.now().plus(5, ChronoUnit.DAYS)))
                .destination(Destination.builder().build())
                .build();
    }
}
