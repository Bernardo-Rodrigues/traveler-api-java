package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.entities.Continent;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
import br.net.traveler.traveler.repositories.ContinentRepository;
import br.net.traveler.traveler.repositories.DestinationRepository;
import br.net.traveler.traveler.repositories.ReviewRepository;
import br.net.traveler.traveler.services.impl.DestinationServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class DestinationServiceUnitTest implements WithAssertions {

    @InjectMocks
    private DestinationServiceImpl destinationService;
    @Mock
    private DestinationRepository destinationRepository;
    @Mock
    private ContinentRepository continentRepository;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private DestinationMapper destinationMapper;

    @Test
    void givenAListDestinationsAttemptWhenThereIsNoNameParameterThenCallListAll(){
        destinationService.list("");

        verify(destinationRepository).findAll();
    }

    @Test
    void givenAListDestinationsAttemptWhenThereIsNoNameParameterThenCallListAllByName(){
        destinationService.list("name");

        verify(destinationRepository).findAllByNameStartsWith("name");
    }

    @Test
    void givenAListTopDestinationsAttemptWhenThereIsContinentNameParameterThenCallFindAllByContinent(){
        Continent continent = Continent.builder().id(1).name("Africa").build();
        given(continentRepository.findByName(continent.getName())).willReturn(continent);

        destinationService.listTop(continent.getName());

        verify(destinationRepository).findAllByContinent(continent.getName());
    }
}
