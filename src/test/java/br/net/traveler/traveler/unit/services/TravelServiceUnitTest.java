package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.dto.TravelDto;
import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.Travel;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.exception.BadRequestException;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mother.DestinationMother;
import br.net.traveler.traveler.domain.mother.TravelMother;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.repositories.DestinationRepository;
import br.net.traveler.traveler.repositories.TravelRepository;
import br.net.traveler.traveler.services.impl.TravelServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class TravelServiceUnitTest implements WithAssertions {

    @InjectMocks
    private TravelServiceImpl travelService;
    @Mock
    private DestinationRepository destinationRepository;
    @Mock
    private TravelRepository travelReopository;

    @Test
    void givenAnAttemptToAddATravelWhenThereIsNoDestinationWithGivenIdThenThrowANotFoundException(){
        Destination destination = DestinationMother.getDestination();
        TravelDto dto = TravelMother.getTravelDto();

        given(destinationRepository.findById(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> travelService.createTravel(dto)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAnAttemptToAddATravelWhenTheDatesAreInvalidThenThrowABadRequestException(){
        Destination destination = DestinationMother.getDestination();
        TravelDto dto = TravelMother.getTravelDto();
        dto.setEndDate(Date.from(Instant.now().minus(7, ChronoUnit.DAYS)));

        given(destinationRepository.findById(destination.getId())).willReturn(Optional.of(destination));

        assertThatThrownBy(() -> travelService.createTravel(dto)).isInstanceOf(BadRequestException.class);
    }

    @Test
    void givenAnAttemptToAddATravelWhenTheDatesConflictsToAnotherTripThenThrowABadRequestException(){
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();
        TravelDto dto = TravelMother.getTravelDto();
        Travel travel = TravelMother.getTravel();

        given(destinationRepository.findById(destination.getId())).willReturn(Optional.of(destination));
        given(travelReopository.findByDate(user.getId(), dto.getStartDate(), dto.getEndDate())).willReturn(travel);

        assertThatThrownBy(() -> travelService.createTravel(dto)).isInstanceOf(BadRequestException.class);
    }
}
