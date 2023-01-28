package br.net.traveler.traveler.domain.mother;

import br.net.traveler.traveler.domain.entities.ReviewAverageScore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor()
public class ReviewAverageScoreMother {
    public static ReviewAverageScore getReviewAverageScore() {
        return ReviewAverageScore.builder()
                .destinationId(1)
                .score(3.0)
                .build();
    }
}
