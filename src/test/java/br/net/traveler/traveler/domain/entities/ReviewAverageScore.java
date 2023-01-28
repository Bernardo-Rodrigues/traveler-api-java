package br.net.traveler.traveler.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewAverageScore implements br.net.traveler.traveler.domain.entities.interfaces.ReviewAverageScore {

    private Integer destinationId;
    private Double score;
}
