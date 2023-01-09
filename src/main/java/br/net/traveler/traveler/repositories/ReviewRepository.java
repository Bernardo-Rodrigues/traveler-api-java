package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.entities.Review;
import br.net.traveler.traveler.entities.pk.ReviewsPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewsPk> {
}