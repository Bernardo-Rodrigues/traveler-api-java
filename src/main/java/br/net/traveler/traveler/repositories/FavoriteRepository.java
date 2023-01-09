package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.entities.Favorite;
import br.net.traveler.traveler.entities.pk.FavoritePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoritePk> {
}
