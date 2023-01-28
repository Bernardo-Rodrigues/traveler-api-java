package br.net.traveler.traveler.domain.mother;

import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.Favorite;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.entities.pk.FavoritePk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor()
public class FavoriteMother {
    public static Favorite getFavorite(User user, Destination destination) {
        return Favorite.builder()
                .id(FavoritePk.builder()
                        .user(user)
                        .destination(destination)
                        .build()
                ).build();
    }
}
