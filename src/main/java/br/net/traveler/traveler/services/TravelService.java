package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.TravelDto;

import java.util.List;

public interface TravelService {

    TravelDto getCurrentTrip(String userId);
    List<TravelDto> listUpcomingTrips(String userId);
    TravelDto createTravel(TravelDto dto);
}
