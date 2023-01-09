package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.entities.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Integer> {
}
