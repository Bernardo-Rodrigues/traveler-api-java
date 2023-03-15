package br.net.traveler.traveler.domain.mapper;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.User;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserMapper {

    UserDto entityToDto(User user);
    User dtoToEntity(UserDto dto);
}
