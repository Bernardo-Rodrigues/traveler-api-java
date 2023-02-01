package br.net.traveler.traveler.domain.dto;

import br.net.traveler.traveler.domain.entities.Destination;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementDto {

    private String name;
    private String description;
    private String imageLink;
    private Integer count;
    private Integer destinationId;
}
