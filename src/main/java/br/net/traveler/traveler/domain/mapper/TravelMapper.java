package br.net.traveler.traveler.domain.mapper;

import br.net.traveler.traveler.domain.dto.TravelDto;
import br.net.traveler.traveler.domain.entities.Travel;
import br.net.traveler.traveler.domain.request.AddTravelRequest;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface TravelMapper {

    TravelDto entityToDto(Travel entity);
    TravelDto createRequestToDto(AddTravelRequest request);
    Travel dtoToEntity(TravelDto dto);
    List<TravelDto> entityListToDtoList(List<Travel> entities);
}
