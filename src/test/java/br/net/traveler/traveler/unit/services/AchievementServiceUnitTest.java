package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.dto.DestinationInformationsDto;
import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
import br.net.traveler.traveler.domain.mother.*;
import br.net.traveler.traveler.repositories.*;
import br.net.traveler.traveler.services.UserService;
import br.net.traveler.traveler.services.impl.AchievementServiceImpl;
import br.net.traveler.traveler.services.impl.DestinationServiceImpl;
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
public class AchievementServiceUnitTest implements WithAssertions {

    @InjectMocks
    private AchievementServiceImpl achievementService;
    @Mock
    private UserService userService;


    @Test
    void givenAnAttemptToListUsersAchievementsWhenThereIsNoUserWithGivenIdThenThrowANotFoundException(){
        User user = UserMother.getUser();

        given(userService.findById(user.getId())).willThrow(NotFoundException.class);

        assertThatThrownBy(() -> achievementService.listByUser(user.getId())).isInstanceOf(NotFoundException.class);
    }
}
