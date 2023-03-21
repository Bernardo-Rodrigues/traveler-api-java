package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.UserDto;

import java.util.List;

public interface AchievementService {

    List<AchievementDto> listByUser(String userId);
    List<AchievementDto> get(UserDto userDto, Integer destinationId);
}
