package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Review;
import br.net.traveler.traveler.domain.entities.ReviewScore;
import br.net.traveler.traveler.domain.entities.pk.ReviewsPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, ReviewsPk> {

    @Query(value = "SELECT r.destination_id as destinationId, AVG(r.note) as score " +
            "FROM reviews r " +
            "GROUP BY r.destination_id",
            nativeQuery = true)
    public List<ReviewScore> listScores();
}