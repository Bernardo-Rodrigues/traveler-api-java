package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Localization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizationRepository extends JpaRepository<Localization, Integer> {
}
