package br.net.traveler.traveler.entities.pk;

import br.net.traveler.traveler.entities.Destination;
import br.net.traveler.traveler.entities.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Embeddable
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsPk {

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "destinationId")
    private Destination destination;
}
