package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.entities.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
}