package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.response.UserAuthenticationResponse;
import br.net.traveler.traveler.domain.response.UserRegistrationResponse;

public interface UserService {

    UserDto createUser(UserDto user);
    String identifyUser(UserDto user);
}
