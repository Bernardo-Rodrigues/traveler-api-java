package br.net.traveler.traveler.domain.mother;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.request.UserDeletionRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor()
public class UserMother {

    public static User getUser() {
        return User.builder()
                .id("id1")
                .build();
    }

    public static UserDto getUserDto() {
        return UserDto.builder()
                .id("id1")
                .build();
    }

    public static UserRegistrationRequest getUserRegistrationRequest(){
        return UserRegistrationRequest.builder()
                .id("id3")
                .build();
    }

    public static UserDeletionRequest getUserDeletionRequest(){
        return UserDeletionRequest.builder()
                .id("id1")
                .build();
    }
}
