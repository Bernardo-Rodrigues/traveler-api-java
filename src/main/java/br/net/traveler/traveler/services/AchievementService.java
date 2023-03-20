package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.AchievementDto;

import java.util.List;

public interface AchievementService {

    List<AchievementDto> listByUser(String userId);
    List<AchievementDto> get(String userId, Integer destinationId);
}
