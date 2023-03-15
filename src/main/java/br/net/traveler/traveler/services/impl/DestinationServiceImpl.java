package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.*;
import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.entities.interfaces.ReviewAverageScore;
import br.net.traveler.traveler.domain.entities.pk.FavoritePk;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
import br.net.traveler.traveler.domain.mapper.TipMapper;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.repositories.*;
import br.net.traveler.traveler.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private DestinationMapper destinationMapper;
    @Autowired
    private TipMapper tipMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContinentRepository continentRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private AchievementUserRepository achievementUserRepository;
    @Autowired
    private TipRepository tipRepository;

    @Override
    public List<DestinationDto> list(String name) {
        List<Destination> destinations;

        if(name == null || name == ""){
            destinations = destinationRepository.findAll();
        } else {
            destinations = destinationRepository.findAllByNameStartsWith(name);
        }

        return destinationMapper.entityListToDtoList(destinations);
    }

    @Override
    public DestinationDto findById(Integer destinationId) {
        try {
            Destination destination = destinationRepository.findById(destinationId).get();
            return destinationMapper.entityToDto(destination);
        } catch (Exception e) {
            throw new NotFoundException("Destination not found");
        }
    }

    @Override
    public List<DestinationWithScoreDto> listTop(String continentName) {
        List<Destination> destinations;
        Continent continent = null;

        if(continentName != null && !continentName.equals("")){
            continent = continentRepository.findByName(continentName);
        }

        if (continent != null) {
            destinations = destinationRepository.findAllByContinent(continentName);
        } else {
            destinations = destinationRepository.findAll();
        }

        List<DestinationWithScoreDto> destinationsWithScoreList = getDestinationsWithScore(destinations);

        return destinationsWithScoreList;
    }

    @Override
    public List<FavoriteDestinationWithScoreDto> listFavorites(String userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);

        List<FavoriteDestinationWithScoreDto> favoritesWithScoreList = getFavoritesWithScore(favorites);

        return favoritesWithScoreList;
    }

    @Override
    public List<TipDto> listTips(Integer destinationId) {
        findDestinationOrThrowNotFound(destinationId);

        return tipMapper.entityListToDtoList(tipRepository.findByDestinationId(destinationId));
    }

    @Override
    public Void favorite(UserDto userDto, Integer destinationId) {
        Destination destination = findDestinationOrThrowNotFound(destinationId);

        Favorite favorite = Favorite.builder().id(FavoritePk.builder().user(userMapper.dtoToEntity(userDto)).destination(destination).build()).build();

        favoriteRepository.save(favorite);
        return null;
    }

    @Override
    public Void unfavorite(String userId, Integer destinationId) {
        findDestinationOrThrowNotFound(destinationId);

        Favorite favorite = favoriteRepository.findByUserIdAndDestinationId(userId, destinationId);

        favoriteRepository.delete(favorite);
        return null;
    }

    @Override
    public DestinationInformationsDto find(String userId, Integer destinationId) {
        Destination destination = findDestinationOrThrowNotFound(destinationId);

        DestinationInformationsDto dto = getDtoWithExtraInformations(userId, destination);
        return dto;
    }

    private DestinationInformationsDto getDtoWithExtraInformations(String userId, Destination destination){
        DestinationInformationsDto dto = DestinationInformationsDto.builder()
                .name(destination.getName())
                .imageLink(destination.getImageLink())
                .localizationId(destination.getLocalization().getId())
                .countryId(destination.getCountry().getId())
                .build();

        Favorite favorited = favoriteRepository.findByUserIdAndDestinationId(userId, destination.getId());
        if (favorited != null) dto.setFavorited(true);

        AchievementUser visited = achievementUserRepository.findByUserIdAndDestinationId(userId, destination.getId());
        if (visited != null) dto.setVisited(true);

        Review personalNote = reviewRepository.findByUserIdAndDestinationId(userId, destination.getId());
        if (personalNote != null) dto.setPersonalNote(personalNote.getNote());

        ReviewAverageScore score = reviewRepository.findAverageScoreByDestinationId(destination.getId());
        if (score != null) dto.setScore(score.getScore());

        return  dto;
    }

    private Destination findDestinationOrThrowNotFound(Integer destinationId){
        try {
            Destination destination = destinationRepository.findById(destinationId).get();
            return  destination;
        } catch (Exception e){
            throw new NotFoundException("Destination not found");
        }
    }

    private Map<Integer, Double> getDestinationScores(){
        Map<Integer, Double> hashMap = new HashMap<>();
        List<ReviewAverageScore> averageScores = reviewRepository.listScores();

        averageScores.forEach(score -> {
            hashMap.put(score.getDestinationId(), score.getScore());
        });

        return hashMap;
    }

    private List<DestinationWithScoreDto> getDestinationsWithScore(List<Destination> destinations){
        List<DestinationWithScoreDto> destinationsWithScoreList = new ArrayList<>();

        Map<Integer, Double> hashMap = getDestinationScores();

        destinations.forEach(destination -> {
            destinationsWithScoreList.add(
                    DestinationWithScoreDto.builder()
                            .name(destination.getName())
                            .imageLink(destination.getImageLink())
                            .localizationId(destination.getLocalization().getId())
                            .countryId(destination.getCountry().getId())
                            .score(hashMap.get(destination.getId()))
                            .build()
            );
        });

        return destinationsWithScoreList;
    }

    private List<FavoriteDestinationWithScoreDto> getFavoritesWithScore(List<Favorite> favorites){
        List<FavoriteDestinationWithScoreDto> favoritesWithScoreList = new ArrayList<>();

        Map<Integer, Double> hashMap = getDestinationScores();

        favorites.forEach(favorite -> {
            favoritesWithScoreList.add(
                    FavoriteDestinationWithScoreDto.builder()
                            .name(favorite.getId().getDestination().getName())
                            .imageLink(favorite.getId().getDestination().getImageLink())
                            .countryName(favorite.getId().getDestination().getCountry().getName())
                            .score(hashMap.get(favorite.getId().getDestination().getId()))
                            .build()
            );
        });

        return favoritesWithScoreList;
    }
}
