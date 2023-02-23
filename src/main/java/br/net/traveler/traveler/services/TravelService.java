package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.ContinentDto;
import br.net.traveler.traveler.domain.dto.TravelDto;

import java.util.List;

public interface TravelService {

    TravelDto getCurrentTrip(Integer userId);
    List<TravelDto> listUpcomingTrips(Integer userId);
    TravelDto createTravel(TravelDto dto);
}
