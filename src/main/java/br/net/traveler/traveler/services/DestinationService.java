package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.DestinationDto;

import java.util.List;

public interface DestinationService {

    List<DestinationDto> list(String name);
}
