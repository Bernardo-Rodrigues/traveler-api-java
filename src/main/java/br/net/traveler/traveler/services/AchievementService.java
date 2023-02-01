package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.ContinentDto;

import java.util.List;

public interface AchievementService {

    List<AchievementDto> listByUser(Integer userId);
}
