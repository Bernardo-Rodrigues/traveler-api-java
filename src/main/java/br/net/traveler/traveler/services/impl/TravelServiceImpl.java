package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.TravelDto;
import br.net.traveler.traveler.domain.entities.Achievement;
import br.net.traveler.traveler.domain.entities.AchievementUser;
import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mapper.TravelMapper;
import br.net.traveler.traveler.repositories.AchievementUserRepository;
import br.net.traveler.traveler.repositories.TravelRepository;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.AchievementService;
import br.net.traveler.traveler.services.TravelService;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TravelRepository travelRepository;
    @Autowired
    TravelMapper travelMapper;

    @Override
    public TravelDto getCurrentTrip(Integer userId) {
        findUserOrThrowNotFound(userId);
        Date now = new Date();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        System.out.println(now);
        System.out.println(yesterday);

        TravelDto dto = travelMapper.entityToDto(travelRepository.findCurrentTrip(userId, now,  yesterday.getTime()));

        return dto;
    }

    private User findUserOrThrowNotFound(Integer userId){
        try {
            User user = userRepository.findById(userId).get();
            return user;
        } catch (Exception e){
            throw new NotFoundException("User not found");
        }
    }
}
