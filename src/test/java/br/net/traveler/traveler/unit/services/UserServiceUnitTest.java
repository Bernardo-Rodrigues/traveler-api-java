package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.domain.exception.UnauthorizedException;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.entities.User;
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

    @Test
    void givenAUserRegistrationAttemptWhenAlreadyExistsAnUserWithTheSameNameThenThrowConflictError(){
        User user = UserMother.getUser();

        given(userRepository.findByUsername(user.getUsername())).willReturn(user);

        assertThatThrownBy(() -> userService.createUser(user)).isInstanceOf(ConflictException.class);
    }

    @Test
    void givenAUserRegistrationAttemptWhenAlreadyExistsAnUserWithTheSameEmailThenThrowConflictError(){
        User user = UserMother.getUser();

        given(userRepository.findByUsername(user.getUsername())).willReturn(null);
        given(userRepository.findByEmail(user.getEmail())).willReturn(user);

        assertThatThrownBy(() -> userService.createUser(user)).isInstanceOf(ConflictException.class);
    }

    @Test
    void givenAUserLoginAttemptWhenTheEmailIsIncorrectThenThrowUnauthorizedError(){
        User user = UserMother.getUser();

        given(userRepository.findByEmail(user.getEmail())).willReturn(null);

        assertThatThrownBy(() -> userService.identifyUser(user)).isInstanceOf(UnauthorizedException.class);
    }

    @Test
    void givenAUserLoginAttemptWhenThePasswordIsIncorrectThenThrowUnauthorizedError(){
        User user = UserMother.getUser();

        given(userRepository.findByEmail(user.getEmail())).willReturn(user);
        given(cryptographyService.matches(eq(user.getPassword()), anyString())).willReturn(false);

        assertThatThrownBy(() -> userService.identifyUser(user)).isInstanceOf(UnauthorizedException.class);
    }
}
