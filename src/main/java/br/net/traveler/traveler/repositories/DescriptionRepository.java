package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.entities.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description, Integer> {
}