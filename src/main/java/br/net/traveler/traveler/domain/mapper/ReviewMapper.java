package br.net.traveler.traveler.domain.mapper;

import br.net.traveler.traveler.domain.dto.ReviewDto;
import br.net.traveler.traveler.domain.entities.Review;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ReviewMapper {

    ReviewDto entityToDto(Review entity);
}
