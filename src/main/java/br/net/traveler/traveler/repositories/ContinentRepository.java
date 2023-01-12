package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {
}
