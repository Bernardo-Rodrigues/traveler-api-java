package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.AchievementUser;
import br.net.traveler.traveler.domain.entities.pk.AchievementUserPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AchievementUserRepository extends JpaRepository<AchievementUser, AchievementUserPk> {
    @Query(value = "SELECT * " +
            "FROM achievements_users au " +
            "WHERE au.user_id = ?1 AND au.achievement_id = ?2",
            nativeQuery = true)
    public AchievementUser findByUserIdAndAchievementId(String userId, Integer achievementId);
    @Query(value = "SELECT * " +
            "FROM achievements_users au " +
            "JOIN achievements a ON a.id = au.achievement_id " +
            "WHERE au.user_id = ?1 AND a.destination_id = ?2",
            nativeQuery = true)
    public AchievementUser findByUserIdAndDestinationId(String userId, Integer destinationId);
    @Query(value = "SELECT * " +
            "FROM achievements_users au " +
            "WHERE au.user_id = ?1",
            nativeQuery = true)
    public List<AchievementUser> findByUserId(String userId);
    @Query(value = "SELECT * " +
            "FROM achievements_users au " +
            "JOIN achievements a ON a.id = au.achievement_id " +
            "WHERE au.user_id = ?1 " +
            "AND a.count IS NULL",
            nativeQuery = true)
    public List<AchievementUser> findAllOfDestinationsByUserId(String userId);
}
