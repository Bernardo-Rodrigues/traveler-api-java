package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.*;

import java.util.List;

public interface DestinationService {

    List<DestinationDto> list(String name);
    DestinationDto findById(Integer destinationId);
    List<DestinationWithScoreDto> listTop(String continentName);
    List<FavoriteDestinationWithScoreDto> listFavorites(String userId);
    List<TipDto> listTips(Integer destinationId);
    Void favorite(UserDto userDto, Integer destinationId);
    Void unfavorite(String userId, Integer destinationId);
    DestinationInformationsDto find(String userId, Integer destinationId);
}
