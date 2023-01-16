package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.UserDto;

public interface JwtService {

    String generateToken(UserDto userDto);

    Boolean validateToken(String token, UserDto userDto);
}
