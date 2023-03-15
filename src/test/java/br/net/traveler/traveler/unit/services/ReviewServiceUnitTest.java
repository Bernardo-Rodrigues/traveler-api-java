package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mother.*;
import br.net.traveler.traveler.repositories.*;
import br.net.traveler.traveler.services.impl.ReviewServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class ReviewServiceUnitTest implements WithAssertions {

    @InjectMocks
    private ReviewServiceImpl reviewService;
    @Mock
    private DestinationRepository destinationRepository;

    @Test
    void givenAnAttemptToAddAReviewToADestinationWhenThereIsNoDestinationWithGivenIdThenThrowANotFoundException(){
        UserDto userDto = UserMother.getUserDto();
        Destination destination = DestinationMother.getDestination();

        given(destinationRepository.findById(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> reviewService.createReview(userDto, destination.getId(), 5)).isInstanceOf(NotFoundException.class);
    }
}
