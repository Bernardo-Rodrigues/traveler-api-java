package br.net.traveler.traveler.domain.mapper;

import br.net.traveler.traveler.domain.dto.ContinentDto;
import br.net.traveler.traveler.domain.dto.DestinationDto;
import br.net.traveler.traveler.domain.dto.TipDto;
import br.net.traveler.traveler.domain.entities.Continent;
import br.net.traveler.traveler.domain.entities.Tip;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ContinentMapper {

    List<ContinentDto> entityListToDtoList(List<Continent> entities);
}
