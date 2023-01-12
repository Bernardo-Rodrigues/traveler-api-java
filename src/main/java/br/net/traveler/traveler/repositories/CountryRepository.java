package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
