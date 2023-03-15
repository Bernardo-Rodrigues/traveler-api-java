package br.net.traveler.traveler.domain.mapper;

import br.net.traveler.traveler.domain.dto.DestinationDto;
import br.net.traveler.traveler.domain.dto.TipDto;
import br.net.traveler.traveler.domain.entities.Tip;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface TipMapper {

    List<TipDto> entityListToDtoList(List<Tip> entities);
    List<Tip> dtoListToEntityList(List<DestinationDto>  dtos);
}
