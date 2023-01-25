package br.net.traveler.traveler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationDto {

    private String name;
    private String imageLink;
    private Integer localizationId;
    private Integer countryId;
}
