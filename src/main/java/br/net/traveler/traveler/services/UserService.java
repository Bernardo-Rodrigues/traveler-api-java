package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto findById(Integer id);
    UserDto updateUser(UserDto user, Integer id);
    UserDto identifyUser(UserDto user);
}
