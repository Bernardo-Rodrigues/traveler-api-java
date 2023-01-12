package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Review;
import br.net.traveler.traveler.domain.entities.pk.ReviewsPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewsPk> {
}