package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.ContinentDto;
import br.net.traveler.traveler.domain.entities.Achievement;
import br.net.traveler.traveler.domain.entities.AchievementUser;
import br.net.traveler.traveler.domain.mapper.AchievementsMapper;
import br.net.traveler.traveler.domain.mapper.ContinentMapper;
import br.net.traveler.traveler.repositories.AchievementRepository;
import br.net.traveler.traveler.repositories.AchievementUserRepository;
import br.net.traveler.traveler.repositories.ContinentRepository;
import br.net.traveler.traveler.services.AchievementService;
import br.net.traveler.traveler.services.ContinentService;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    AchievementUserRepository achievementUserRepository;
    @Autowired
    UserService userService;


    @Override
    public List<AchievementDto> listByUser(Integer userId) {
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
}
