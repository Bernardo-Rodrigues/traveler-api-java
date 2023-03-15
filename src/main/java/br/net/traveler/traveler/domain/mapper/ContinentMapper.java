package br.net.traveler.traveler.domain.mapper;

import br.net.traveler.traveler.domain.dto.ContinentDto;
import br.net.traveler.traveler.domain.entities.Continent;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ContinentMapper {

    List<ContinentDto> entityListToDtoList(List<Continent> entities);
}
