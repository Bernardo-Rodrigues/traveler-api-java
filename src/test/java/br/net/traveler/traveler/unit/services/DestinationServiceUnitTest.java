package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.entities.Continent;
import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
import br.net.traveler.traveler.domain.mother.DestinationMother;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.repositories.ContinentRepository;
import br.net.traveler.traveler.repositories.DestinationRepository;
import br.net.traveler.traveler.repositories.ReviewRepository;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.impl.DestinationServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

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
    @Mock
    private UserRepository userRepository;

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

    @Test
    void givenAnAttemptToFavoriteADestinationWhenThereIsNoUserWithGivenIdThenThrowANotFoundException(){
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();

        given(userRepository.findById(user.getId())).willReturn(null);

        assertThatThrownBy(() -> destinationService.favorite(user.getId(), destination.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAnAttemptToFavoriteADestinationWhenThereIsNoDestinationWithGivenIdThenThrowANotFoundException(){
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        given(destinationRepository.findById(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> destinationService.favorite(user.getId(), destination.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAnAttemptToUnfavoriteADestinationWhenThereIsNoUserWithGivenIdThenThrowANotFoundException(){
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();

        given(userRepository.findById(user.getId())).willReturn(null);

        assertThatThrownBy(() -> destinationService.unfavorite(user.getId(), destination.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAnAttemptToUnfavoriteADestinationWhenThereIsNoDestinationWithGivenIdThenThrowANotFoundException(){
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        given(destinationRepository.findById(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> destinationService.unfavorite(user.getId(), destination.getId())).isInstanceOf(NotFoundException.class);
    }
}
