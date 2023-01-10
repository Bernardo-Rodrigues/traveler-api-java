package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.entities.User;
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

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void givenANewUserWhenAlreadyExistsAnUserWithTheSameNameThenThrowConflictError(){
        User user = UserMother.getUser();

        given(userRepository.findByUsername("username")).willReturn(user);

        assertThatThrownBy(() -> userService.saveUser(user)).isInstanceOf(ConflictException.class);

    }
}
