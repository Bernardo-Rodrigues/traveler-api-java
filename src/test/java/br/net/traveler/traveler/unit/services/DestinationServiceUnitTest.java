package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.dto.DestinationInformationsDto;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
import br.net.traveler.traveler.domain.mother.*;
import br.net.traveler.traveler.repositories.*;
import br.net.traveler.traveler.services.impl.DestinationServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @Mock
    private FavoriteRepository favoriteRepository;
    @Mock
    private AchievementUserRepository achievementUserRepository ;

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
    void givenAnAttemptToFavoriteADestinationWhenThereIsNoDestinationWithGivenIdThenThrowANotFoundException(){
        UserDto userDto = UserMother.getUserDto();
        Destination destination = DestinationMother.getDestination();

        given(destinationRepository.findById(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> destinationService.favorite(userDto, destination.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAnAttemptToUnfavoriteADestinationWhenThereIsNoDestinationWithGivenIdThenThrowANotFoundException(){
        UserDto userDto = UserMother.getUserDto();
        Destination destination = DestinationMother.getDestination();

        given(destinationRepository.findById(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> destinationService.unfavorite(userDto.getId(), destination.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAnAttemptToFindADestinationWhenThereIsNoDestinationWithGivenNameThenThrowANotFoundException(){
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();

        given(destinationRepository.findById(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> destinationService.find(user.getId(), destination.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAnAttemptToFindADestinationWhenDestinationsExistsThenSetPropertiesCorrectly(){
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();
        Favorite favorite = FavoriteMother.getFavorite(user, destination);
        AchievementUser achievementUser = AchievementUserMother.getAchievementUser(user);
        Review review = ReviewMother.getReview(user, destination);
        ReviewAverageScore reviewAverageScore = ReviewAverageScoreMother.getReviewAverageScore();

        given(destinationRepository.findById(destination.getId())).willReturn(Optional.of(destination));
        given(favoriteRepository.findByUserIdAndDestinationId(user.getId(), destination.getId())).willReturn(favorite);
        given(achievementUserRepository.findByUserIdAndDestinationId(user.getId(), destination.getId())).willReturn(achievementUser);
        given(reviewRepository.findByUserIdAndDestinationId(user.getId(), destination.getId())).willReturn(review);
        given(reviewRepository.findAverageScoreByDestinationId(destination.getId())).willReturn(reviewAverageScore);

        DestinationInformationsDto dto = destinationService.find(user.getId(), destination.getId());

        assertThat(dto.getFavorited()).isTrue();
        assertThat(dto.getVisited()).isTrue();
        assertThat(dto.getPersonalNote()).isEqualTo(5);
        assertThat(dto.getScore()).isEqualTo(3);
    }

    @Test
    void givenAnAttemptToListDestinationTipsWhenThereIsNoDestinationWithGivenIdThenThrowANotFoundException(){
        Destination destination = DestinationMother.getDestination();

        given(destinationRepository.findById(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> destinationService.listTips(destination.getId())).isInstanceOf(NotFoundException.class);
    }
}
