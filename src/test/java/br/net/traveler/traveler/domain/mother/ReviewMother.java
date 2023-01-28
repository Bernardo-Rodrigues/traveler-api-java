package br.net.traveler.traveler.domain.mother;

import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.Review;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.entities.pk.ReviewsPk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor()
public class ReviewMother {
    public static Review getReview(User user, Destination destination) {
        return Review.builder()
                .id(ReviewsPk.builder()
                        .user(user)
                        .destination(destination)
                        .build()
                ).note(5)
                .build();
    }
}
