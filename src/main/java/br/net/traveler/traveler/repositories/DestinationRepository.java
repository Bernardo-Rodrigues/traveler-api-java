package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
}
