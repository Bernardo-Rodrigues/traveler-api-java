package br.net.traveler.traveler.domain.mapper;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.TravelDto;
import br.net.traveler.traveler.domain.entities.Achievement;
import br.net.traveler.traveler.domain.entities.Travel;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface TravelMapper {

    TravelDto entityToDto(Travel entity);
}
