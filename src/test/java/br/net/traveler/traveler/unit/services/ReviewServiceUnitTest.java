package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.dto.DestinationInformationsDto;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
import br.net.traveler.traveler.domain.mapper.ReviewMapper;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.mother.*;
import br.net.traveler.traveler.repositories.*;
import br.net.traveler.traveler.services.DestinationService;
import br.net.traveler.traveler.services.UserService;
import br.net.traveler.traveler.services.impl.DestinationServiceImpl;
import br.net.traveler.traveler.services.impl.ReviewServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
public class ReviewServiceUnitTest implements WithAssertions {

    @InjectMocks
    private ReviewServiceImpl reviewService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private DestinationRepository destinationRepository;

    @Test
    void givenAnAttemptToAddAReviewToADestinationWhenThereIsNoUserWithGivenIdThenThrowANotFoundException(){
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();

        given(userRepository.findById(user.getId())).willReturn(null);

        assertThatThrownBy(() -> reviewService.createReview(user.getId(), destination.getId(), 5)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAnAttemptToAddAReviewToADestinationWhenThereIsNoDestinationWithGivenIdThenThrowANotFoundException(){
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        given(destinationRepository.findById(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> reviewService.createReview(user.getId(), destination.getId(), 5)).isInstanceOf(NotFoundException.class);
    }
}
