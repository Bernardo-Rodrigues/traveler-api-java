package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.entities.AchievementUser;
import br.net.traveler.traveler.entities.pk.AchievementUserPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementUserRepository extends JpaRepository<AchievementUser, AchievementUserPk> {
}
