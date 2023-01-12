package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Favorite;
import br.net.traveler.traveler.domain.entities.pk.FavoritePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoritePk> {
}
