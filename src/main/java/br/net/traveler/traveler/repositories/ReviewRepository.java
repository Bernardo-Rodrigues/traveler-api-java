package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Favorite;
import br.net.traveler.traveler.domain.entities.Review;
import br.net.traveler.traveler.domain.entities.interfaces.ReviewAverageScore;
import br.net.traveler.traveler.domain.entities.pk.ReviewsPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, ReviewsPk> {

    @Query(value = "SELECT r.destination_id as destinationId, AVG(r.note) as score " +
            "FROM reviews r " +
            "GROUP BY r.destination_id",
            nativeQuery = true)
    public List<ReviewAverageScore> listScores();
    @Query(value = "SELECT r.destination_id as destinationId, AVG(r.note) as score " +
            "FROM reviews r " +
            "WHERE r.destination_id = ?1 " +
            "GROUP BY r.destination_id",
            nativeQuery = true)
    public ReviewAverageScore findAverageScoreByDestinationId(Integer destinationId);
    @Query(value = "SELECT * " +
            "FROM reviews r " +
            "WHERE r.user_id = ?1 AND r.destination_id = ?2",
            nativeQuery = true)
    public Review findByUserIdAndDestinationId(Integer userId, Integer destinationId);
}