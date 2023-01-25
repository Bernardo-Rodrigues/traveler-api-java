package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    public List<Destination> findAllByNameStartsWith(String name);
}
