package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.exception.UnauthorizedException;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.CryptographyService;
import br.net.traveler.traveler.services.impl.UserServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class UserServiceUnitTest implements WithAssertions {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CryptographyService cryptographyService;

    @Mock
    private UserMapper userMapper;

    @Test
    void givenAUserRegistrationAttemptWhenAlreadyExistsAnUserWithTheSameNameThenThrowConflictError(){
        UserDto dto = UserMother.getUserDto();

        given(userRepository.findByUsername(dto.getUsername())).willReturn(UserMother.getUser());

        assertThatThrownBy(() -> userService.createUser(dto)).isInstanceOf(ConflictException.class);
    }

    @Test
    void givenAUserRegistrationAttemptWhenAlreadyExistsAnUserWithTheSameEmailThenThrowConflictError(){
        UserDto dto = UserMother.getUserDto();

        given(userRepository.findByUsername(dto.getUsername())).willReturn(null);
        given(userRepository.findByEmail(dto.getEmail())).willReturn(UserMother.getUser());

        assertThatThrownBy(() -> userService.createUser(dto)).isInstanceOf(ConflictException.class);
    }

    @Test
    void givenAUserLoginAttemptWhenTheEmailIsIncorrectThenThrowUnauthorizedError(){
        UserDto dto = UserMother.getUserDto();

        given(userRepository.findByEmail(dto.getEmail())).willReturn(null);

        assertThatThrownBy(() -> userService.identifyUser(dto)).isInstanceOf(UnauthorizedException.class);
    }

    @Test
    void givenAUserLoginAttemptWhenThePasswordIsIncorrectThenThrowUnauthorizedError(){
        UserDto dto = UserMother.getUserDto();

        given(userRepository.findByEmail(dto.getEmail())).willReturn(UserMother.getUser());
        given(cryptographyService.matches(eq(dto.getPassword()), anyString())).willReturn(false);

        assertThatThrownBy(() -> userService.identifyUser(dto)).isInstanceOf(UnauthorizedException.class);
    }

    @Test
    void givenAUserSearchAttemptWhenTheUserNotExistThenThrowNotFoundError(){
        User user = UserMother.getUser();

        given(userRepository.findById(user.getId())).willReturn(null);

        assertThatThrownBy(() -> userService.findById(user.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAUserUpdateAttemptWhenTheUserNotExistThenThrowNotFoundError(){
        User user = UserMother.getUser();
        UserDto userToUpdate = UserMother.getUserUpdateDto();

        given(userRepository.getReferenceById(user.getId())).willReturn(null);

        assertThatThrownBy(() -> userService.updateUser(userToUpdate, user.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void givenAUserUpdateAttemptWhenAnotherUserWithSameUsernameExistsThenThrowConflictError(){
        UserDto userToUpdate = UserMother.getUserUpdateDto();

        given(userRepository.getReferenceById(userToUpdate.getId())).willReturn(UserMother.getUserToUpdate());
        given(userRepository.findByUsername(userToUpdate.getUsername())).willReturn(UserMother.getUser());

        assertThatThrownBy(() -> userService.updateUser(userToUpdate, userToUpdate.getId())).isInstanceOf(ConflictException.class);
    }

    @Test
    void givenAUserUpdateAttemptWhenAnotherUserWithSameEmailExistsThenThrowConflictError(){
        UserDto userToUpdate = UserMother.getUserUpdateDto();

        given(userRepository.getReferenceById(userToUpdate.getId())).willReturn(UserMother.getUserToUpdate());
        given(userRepository.findByUsername(userToUpdate.getUsername())).willReturn(null);
        given(userRepository.findByEmail(userToUpdate.getEmail())).willReturn(UserMother.getUser());

        assertThatThrownBy(() -> userService.updateUser(userToUpdate, userToUpdate.getId())).isInstanceOf(ConflictException.class);
    }
}
