package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.DestinationDto;
import br.net.traveler.traveler.domain.dto.DestinationInformationsDto;
import br.net.traveler.traveler.domain.dto.DestinationWithScoreDto;
import br.net.traveler.traveler.domain.dto.FavoriteDestinationWithScoreDto;
import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.entities.interfaces.ReviewAverageScore;
import br.net.traveler.traveler.domain.entities.pk.FavoritePk;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
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
    DestinationRepository destinationRepository;

    @Autowired
    DestinationMapper mapper;
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


    @Override
    public List<DestinationDto> list(String name) {
        List<Destination> destinations;

        if(name == null || name == ""){
            destinations = destinationRepository.findAll();
        } else {
            destinations = destinationRepository.findAllByNameStartsWith(name);
        }

        return mapper.entityListToDtoList(destinations);
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
    public List<FavoriteDestinationWithScoreDto> listFavorites(Integer userId) {
        findUserOrThrowNotFound(userId);
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);

        List<FavoriteDestinationWithScoreDto> favoritesWithScoreList = getFavoritesWithScore(favorites);

        return favoritesWithScoreList;
    }

    @Override
    public Void favorite(Integer userId, Integer destinationId) {
        User user = findUserOrThrowNotFound(userId);
        Destination destination = findDestinationOrThrowNotFound(destinationId);

        Favorite favorite = Favorite.builder().id(FavoritePk.builder().user(user).destination(destination).build()).build();

        favoriteRepository.save(favorite);
        return null;
    }

    @Override
    public Void unfavorite(Integer userId, Integer destinationId) {
        findUserOrThrowNotFound(userId);
        findDestinationOrThrowNotFound(destinationId);

        Favorite favorite = favoriteRepository.findByUserIdAndDestinationId(userId, destinationId);

        favoriteRepository.delete(favorite);
        return null;
    }

    @Override
    public DestinationInformationsDto find(Integer userId, Integer destinationId) {
        findUserOrThrowNotFound(userId);
        Destination destination = findDestinationOrThrowNotFound(destinationId);

        DestinationInformationsDto dto = getDtoWithExtraInformations(userId, destination);
        return dto;
    }

    private DestinationInformationsDto getDtoWithExtraInformations(Integer userId, Destination destination){
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

    private User findUserOrThrowNotFound(Integer userId){
        try {
            User user = userRepository.findById(userId).get();
            return user;
        } catch (Exception e){
            throw new NotFoundException("User not found");
        }
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
