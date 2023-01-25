package br.net.traveler.traveler.domain.mapper;

import br.net.traveler.traveler.domain.dto.DestinationDto;
import br.net.traveler.traveler.domain.entities.Destination;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface DestinationMapper {

    List<DestinationDto> entityListToDtoList(List<Destination> dtos);
    List<Destination> dtoListToEntityList(List<DestinationDto>  dtos);
}
