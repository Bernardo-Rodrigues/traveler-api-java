package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.entities.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipRepository extends JpaRepository<Tip, Integer> {
}
