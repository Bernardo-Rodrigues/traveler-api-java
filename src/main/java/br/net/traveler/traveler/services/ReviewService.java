package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(Integer userId, Integer destinationId, Integer note);
}
