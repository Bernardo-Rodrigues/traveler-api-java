package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.AchievementUser;
import br.net.traveler.traveler.domain.entities.Favorite;
import br.net.traveler.traveler.domain.entities.pk.AchievementUserPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AchievementUserRepository extends JpaRepository<AchievementUser, AchievementUserPk> {
    @Query(value = "SELECT * " +
            "FROM achievements_users au " +
            "WHERE au.user_id = ?1 AND au.achievement_id = ?2",
            nativeQuery = true)
    public AchievementUser findByUserIdAndDestinationId(Integer userId, Integer achievementId);
}
