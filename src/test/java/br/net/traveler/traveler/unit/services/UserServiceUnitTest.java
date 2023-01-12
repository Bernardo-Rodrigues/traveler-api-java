package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.exception.ConflictException;
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
import org.springframework.beans.factory.annotation.Autowired;


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
}
