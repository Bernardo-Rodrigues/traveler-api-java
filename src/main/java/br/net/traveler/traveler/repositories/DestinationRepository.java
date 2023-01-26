package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    public List<Destination> findAllByNameStartsWith(String name);

    @Query(value = "SELECT d.* " +
            "FROM destinations d " +
            "JOIN countries cou on cou.id = d.country_id " +
            "JOIN continents con on con.id = cou.continent_id " +
            "WHERE con.name = ?1",
            nativeQuery = true)
    public List<Destination> findAllByContinent(String continentName);
}
