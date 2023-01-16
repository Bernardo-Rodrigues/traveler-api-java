package br.net.traveler.traveler.domain.mapper;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.request.UserAuthenticationRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import br.net.traveler.traveler.domain.request.UserUpdateRequest;
import br.net.traveler.traveler.domain.response.UserAuthenticationResponse;
import br.net.traveler.traveler.domain.response.UserRegistrationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserMapper {

    UserDto entityToDto(User user);
    User dtoToEntity(UserDto dto);
    UserDto registrationRequestToDto(UserRegistrationRequest registrationRequest);
    UserDto authenticationRequestToDto(UserAuthenticationRequest authenticationRequest);
    UserDto updateRequestToDto(UserUpdateRequest updateRequest);
    UserRegistrationResponse dtoToRegistrationResponse(UserDto dto);
    UserAuthenticationResponse dtoToAuthenticationResponse(UserDto dto);
}
