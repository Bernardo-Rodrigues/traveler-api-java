package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.UserDto;

public interface UserService {

    UserDto createUser(String id);
    void deleteUser(String id);
}
