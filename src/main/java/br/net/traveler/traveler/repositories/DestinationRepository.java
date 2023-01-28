package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    @Query(value = "SELECT d.* " +
            "FROM destinations d " +
            "JOIN countries cou on cou.id = d.country_id " +
            "JOIN continents con on con.id = cou.continent_id " +
            "WHERE con.name = ?1",
            nativeQuery = true)
    public List<Destination> findAllByContinent(String continentName);
    public List<Destination> findAllByNameStartsWith(String name);
    public Destination findByName(String name);
    public Destination findByLocalizationId(Integer localizationId);
}
