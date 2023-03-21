package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.exception.ServerException;
import br.net.traveler.traveler.domain.mother.*;
import br.net.traveler.traveler.repositories.*;
import br.net.traveler.traveler.services.impl.AchievementServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class AchievementServiceUnitTest implements WithAssertions {

    @InjectMocks
    private AchievementServiceImpl achievementService;
    @Mock
    private DestinationRepository destinationRepository;
    @Mock
    private AchievementRepository achievementRepository;
    @Mock
    private AchievementUserRepository achievementUserRepository;

    @Test
    void givenAnAttemptToGetADestinationAchievementWhenThereIsNoDestinationWithGivenIdThenThrowANotFoundException(){
        UserDto userDto = UserMother.getUserDto();
        Destination destination = DestinationMother.getDestination();

        given(destinationRepository.findById(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> achievementService.get(userDto, destination.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAnAttemptToGetADestinationAchievementWhenThereIsNoAchievementToThatDestinationThenThrowAServerException(){
        UserDto userDto = UserMother.getUserDto();
        Destination destination = DestinationMother.getDestination();

        given(destinationRepository.findById(destination.getId())).willReturn(Optional.of(destination));
        given(achievementRepository.findByDestinationId(destination.getId())).willReturn(null);

        assertThatThrownBy(() -> achievementService.get(userDto, destination.getId())).isInstanceOf(ServerException.class);
    }

    @Test
    void givenAnAttemptToGetADestinationAchievementWhenTheUserAlreadyHaveThatAchievementThenReturnNull(){
        User user = UserMother.getUser();
        Destination destination = DestinationMother.getDestination();
        Achievement achievement = AchievementMother.getAchievement();
        AchievementUser achievementUser = AchievementUserMother.getAchievementUser(user);

        given(destinationRepository.findById(destination.getId())).willReturn(Optional.of(destination));
        given(achievementRepository.findByDestinationId(destination.getId())).willReturn(achievement);
        given(achievementUserRepository.findByUserIdAndAchievementId(user.getId(), achievement.getId())).willReturn(achievementUser);

        List<AchievementDto> achievements = achievementService.get(UserMother.getUserDto(), destination.getId());

        assertThat(achievements).isNull();
    }


}
