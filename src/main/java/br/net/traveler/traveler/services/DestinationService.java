package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.DestinationDto;
import br.net.traveler.traveler.domain.dto.DestinationInformationsDto;
import br.net.traveler.traveler.domain.dto.DestinationWithScoreDto;
import br.net.traveler.traveler.domain.dto.FavoriteDestinationWithScoreDto;

import java.util.List;

public interface DestinationService {

    List<DestinationDto> list(String name);
    List<DestinationWithScoreDto> listTop(String continentName);
    List<FavoriteDestinationWithScoreDto> listFavorites(Integer userId);
    Void favorite(Integer userId, Integer destinationId);
    Void unfavorite(Integer userId, Integer destinationId);
    DestinationInformationsDto find(Integer userId, Integer destinationId);
}
