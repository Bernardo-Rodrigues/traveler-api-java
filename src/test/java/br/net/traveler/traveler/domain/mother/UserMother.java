package br.net.traveler.traveler.domain.mother;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.request.UserAuthenticationRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import br.net.traveler.traveler.domain.request.UserUpdateRequest;
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

    public static User getUserToUpdate() {
        return User.builder()
                .id(2)
                .username("username")
                .email("email@email.com")
                .password("password")
                .build();
    }

    public static UserDto getUserDto() {
        return UserDto.builder()
                .username("username")
                .email("email@email.com")
                .password("password")
                .build();
    }

    public static UserRegistrationRequest getUserRegistrationRequest(){
        return UserRegistrationRequest.builder()
                .username("username")
                .email("email@email.com")
                .password("password")
                .build();
    }

    public static UserAuthenticationRequest getUserAuthenticationRequest(){
        return UserAuthenticationRequest.builder()
                .email("email1@email.com")
                .password("password1")
                .build();
    }

    public static UserUpdateRequest getUserUpdateRequest(){
        return UserUpdateRequest.builder()
                .username("updated username")
                .email("updatedemail@email.com")
                .password("password")
                .build();
    }

    public static UserDto getUserUpdateDto(){
        return UserDto.builder()
                .id(2)
                .username("updated username")
                .email("email@email.com")
                .password("password")
                .build();
    }
}
