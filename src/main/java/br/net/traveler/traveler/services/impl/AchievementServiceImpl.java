package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.Achievement;
import br.net.traveler.traveler.domain.entities.AchievementUser;
import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.pk.AchievementUserPk;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.exception.ServerException;
import br.net.traveler.traveler.domain.mapper.AchievementsMapper;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.repositories.AchievementRepository;
import br.net.traveler.traveler.repositories.AchievementUserRepository;
import br.net.traveler.traveler.repositories.DestinationRepository;
import br.net.traveler.traveler.services.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    AchievementUserRepository achievementUserRepository;
    @Autowired
    AchievementRepository achievementRepository;
    @Autowired
    DestinationRepository destinationRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AchievementsMapper achievementsMapper;

    @Override
    public List<AchievementDto> listByUser(String userId) {
        List<AchievementUser> achievementUsers = achievementUserRepository.findByUserId(userId);
        List<AchievementDto> dtos = new ArrayList<>();

        achievementUsers.forEach(achievementUser -> {
            Achievement achievement = achievementUser.getId().getAchievement();
            AchievementDto dto = AchievementDto.builder()
                    .name(achievement.getName())
                    .description(achievement.getDescription())
                    .imageLink(achievement.getImageLink())
                    .build();

            if(achievement.getDestination() == null) dto.setCount(achievement.getCount());
            else dto.setDestinationId(achievement.getDestination().getId());

            dtos.add(dto);
        });

        return dtos;
    }

    @Override
    public List<AchievementDto> get(UserDto userDto, Integer destinationId) {
        findDestinationOrThrowNotFound(destinationId);
        Achievement destinationAchievement = findAchievementByDestination(destinationId);
        Boolean userAlreadyHaveTheAchievement = haveTheAchievement(userDto.getId(), destinationAchievement.getId());

        if(userAlreadyHaveTheAchievement == true) return null;

        List<AchievementDto> achievementDtosObtained = new ArrayList<>();
        addUserAchievement(achievementDtosObtained, userDto, destinationAchievement);
        verifyAchievementsCount(achievementDtosObtained, userDto);

        return achievementDtosObtained;
    }

    private Destination findDestinationOrThrowNotFound(Integer destinationId){
        try {
            Destination destination = destinationRepository.findById(destinationId).get();
            return  destination;
        } catch (Exception e){
            throw new NotFoundException("Destination not found");
        }
    }

    private Achievement findAchievementByDestination(Integer destinationId) {
        Achievement achievement = achievementRepository.findByDestinationId(destinationId);

        if (achievement == null) throw new ServerException("Destination does not have achievements");

        return achievement;
    }

    private Boolean haveTheAchievement(String userId, Integer achievementId) {
        AchievementUser achievementUser = achievementUserRepository.findByUserIdAndAchievementId(userId, achievementId);

        if (achievementUser != null) return true;

        return false;
    }

    private void addUserAchievement(List<AchievementDto> achievementDtosObtained, UserDto userDto, Achievement achievement){
        AchievementUser achievementUser = AchievementUser.builder()
                .id(AchievementUserPk.builder()
                        .user(userMapper.dtoToEntity(userDto))
                        .achievement(achievement)
                        .build())
                .build();

        achievementUserRepository.save(achievementUser);
        achievementDtosObtained.add(achievementsMapper.entityToDto(achievement));
    }

    private void verifyAchievementsCount(List<AchievementDto> achievementDtosObtained, UserDto userDto) {
        List<AchievementUser> achievementsOfDestinations = achievementUserRepository.findAllOfDestinationsByUserId(userDto.getId());
        Achievement countAchievement = achievementRepository.findByCount(achievementsOfDestinations.size());

        if(countAchievement != null) addUserAchievement(achievementDtosObtained, userDto, countAchievement);
    }
}
