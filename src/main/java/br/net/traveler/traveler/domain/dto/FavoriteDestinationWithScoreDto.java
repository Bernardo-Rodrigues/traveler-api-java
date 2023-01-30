package br.net.traveler.traveler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDestinationWithScoreDto {

    private String name;
    private String imageLink;
    private String countryName;
    private Double score;
}
