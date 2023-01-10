package br.net.traveler.traveler.entities;

import br.net.traveler.traveler.entities.pk.ReviewsPk;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @EmbeddedId
    private ReviewsPk id;

    private Integer note;

}
