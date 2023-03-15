package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.impl.UserServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class UserServiceUnitTest implements WithAssertions {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void givenAUserDeletionAttemptWhenTheUserNotExistThenThrowNotFoundError(){
        User user = UserMother.getUser();

        given(userRepository.getReferenceById(user.getId())).willReturn(null);

        assertThatThrownBy(() -> userService.deleteUser(user.getId())).isInstanceOf(NotFoundException.class);
    }
}
