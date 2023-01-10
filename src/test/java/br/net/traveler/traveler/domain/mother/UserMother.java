package br.net.traveler.traveler.domain.mother;

import br.net.traveler.traveler.entities.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor()
public class UserMother {

    public static User getUser() {
        return User.builder()
                .id(1)
                .username("username")
                .email("email@email.com")
                .password("password")
                .build();
    }
}
