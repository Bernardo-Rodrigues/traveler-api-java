package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.Favorite;
import br.net.traveler.traveler.domain.entities.pk.FavoritePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoritePk> {

    @Query(value = "SELECT * " +
            "FROM favorites f " +
            "WHERE f.user_id = ?1 AND f.destination_id = ?2",
            nativeQuery = true)
    public Favorite findByUserAndDestination(Integer userId, Integer destinationId);
}
