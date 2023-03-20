package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Achievement;
import br.net.traveler.traveler.domain.entities.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
    public Achievement findByDestinationId(Integer destinationId);
}