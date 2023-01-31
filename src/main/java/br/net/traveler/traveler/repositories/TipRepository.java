package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipRepository extends JpaRepository<Tip, Integer> {
    public List<Tip> findByDestinationId(Integer destinationId);
}
