package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.DestinationDto;
import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
import br.net.traveler.traveler.repositories.DestinationRepository;
import br.net.traveler.traveler.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    DestinationMapper mapper;

    @Override
    public List<DestinationDto> list(String name) {
        List<Destination> destinations;

        if(name == null || name == ""){
            destinations = destinationRepository.findAll();
        } else {
            destinations = destinationRepository.findAllByNameStartsWith(name);
        }

        return mapper.entityListToDtoList(destinations);
    }
}
