package br.net.traveler.traveler.entities;

import br.net.traveler.traveler.entities.pk.FavoritePk;
import br.net.traveler.traveler.entities.pk.ReviewsPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @EmbeddedId
    FavoritePk id;
}
