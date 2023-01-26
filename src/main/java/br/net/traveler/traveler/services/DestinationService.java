package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.DestinationDto;
import br.net.traveler.traveler.domain.dto.DestinationWithScoreDto;

import java.util.List;

public interface DestinationService {

    List<DestinationDto> list(String name);
    List<DestinationWithScoreDto> listTop(String continentName);
}
