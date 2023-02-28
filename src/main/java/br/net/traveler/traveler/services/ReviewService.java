package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.ReviewDto;
import br.net.traveler.traveler.domain.dto.UserDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(UserDto userDto, Integer destinationId, Integer note);
}
