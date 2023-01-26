package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.DestinationDto;
import br.net.traveler.traveler.domain.dto.DestinationWithScoreDto;
import br.net.traveler.traveler.domain.entities.Continent;
import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.ReviewScore;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
import br.net.traveler.traveler.repositories.ContinentRepository;
import br.net.traveler.traveler.repositories.DestinationRepository;
import br.net.traveler.traveler.repositories.ReviewRepository;
import br.net.traveler.traveler.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    DestinationMapper mapper;
    @Autowired
    private ContinentRepository continentRepository;
    @Autowired
    private ReviewRepository reviewRepository;


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

    @Override
    public List<DestinationWithScoreDto> listTop(String continentName) {
        List<Destination> destinations;
        List<DestinationWithScoreDto> destinationsWithScoreList = new ArrayList<>();
        Continent continent = null;

        if(continentName != null && !continentName.equals("")){
            continent = continentRepository.findByName(continentName);
        }

        if (continent != null) {
            destinations = destinationRepository.findAllByContinent(continentName);
        } else {
            destinations = destinationRepository.findAll();
        }

        List<ReviewScore> scores = reviewRepository.listScores();
        Map<Integer, Double> hashMap = new HashMap<>();

        scores.forEach(score -> {
            hashMap.put(score.getDestinationId(), score.getScore());
        });

        destinations.forEach(destination -> {
            destinationsWithScoreList.add(
                    DestinationWithScoreDto.builder()
                            .name(destination.getName())
                            .imageLink(destination.getImageLink())
                            .localizationId(destination.getLocalization().getId())
                            .countryId(destination.getCountry().getId())
                            .score(hashMap.get(destination.getId()))
                    .build()
            );
        });

        return destinationsWithScoreList;
    }
}
