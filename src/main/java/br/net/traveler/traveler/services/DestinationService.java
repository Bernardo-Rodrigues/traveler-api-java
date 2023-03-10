package br.net.traveler.traveler.services;

import br.net.traveler.traveler.domain.dto.*;
import br.net.traveler.traveler.domain.entities.User;

import java.util.List;

public interface DestinationService {

    List<DestinationDto> list(String name);
    DestinationDto findById(Integer destinationId);
    List<DestinationWithScoreDto> listTop(String continentName);
    List<FavoriteDestinationWithScoreDto> listFavorites(Integer userId);
    List<TipDto> listTips(Integer destinationId);
    Void favorite(UserDto userDto, Integer destinationId);
    Void unfavorite(Integer userId, Integer destinationId);
    DestinationInformationsDto find(Integer userId, Integer destinationId);
}
