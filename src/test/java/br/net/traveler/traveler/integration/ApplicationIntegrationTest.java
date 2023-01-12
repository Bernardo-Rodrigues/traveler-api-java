package br.net.traveler.traveler.integration;

import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.entities.User;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.UserService;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationIntegrationTest implements WithAssertions {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void truncateDatabase(){
        userRepository.deleteAll();
    }

    @Test
    void givenANewUserWhenInformationAreOkThenCreateTheUser(){
        User user = UserMother.getUser();

        userService.createUser(user);

        User createdUser = userRepository.findByEmail(user.getEmail());

        assertThat(createdUser)
                .usingRecursiveComparison()
                .comparingOnlyFields("username", "email", "password")
                .isEqualTo(user);
    }
}
