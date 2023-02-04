package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Favorite;
import br.net.traveler.traveler.domain.entities.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Integer> {

    @Query(value = "SELECT * " +
            "FROM travels t " +
            "JOIN destinations d " +
            "ON d.localization_id = t.destination_id " +
            "WHERE t.user_id = ?1 " +
            "AND t.start_date <= ?2 " +
            "AND t.end_date >= ?3 ",
            nativeQuery = true)
    public Travel findCurrentTrip(Integer userId, Date now, Date yesterday);
}
