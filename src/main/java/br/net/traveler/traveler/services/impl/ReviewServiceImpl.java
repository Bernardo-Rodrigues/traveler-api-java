package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.DestinationDto;
import br.net.traveler.traveler.domain.dto.ReviewDto;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.entities.pk.ReviewsPk;
import br.net.traveler.traveler.domain.exception.BadRequestException;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
import br.net.traveler.traveler.domain.mapper.ReviewMapper;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.repositories.AchievementUserRepository;
import br.net.traveler.traveler.repositories.DestinationRepository;
import br.net.traveler.traveler.repositories.ReviewRepository;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.AchievementService;
import br.net.traveler.traveler.services.DestinationService;
import br.net.traveler.traveler.services.ReviewService;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DestinationRepository destinationRepository;
    @Autowired
    UserService userService;
    @Autowired
    DestinationService destinationService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DestinationMapper destinationMapper;
    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public ReviewDto createReview(UserDto userDto, Integer destinationId, Integer note) {
        Destination destination = findDestinationOrThrowNotFound(destinationId);

        if(note < 1 || note > 5) throw new BadRequestException("Note ut of range");

        Review review = reviewRepository.save(Review.builder()
                .id(ReviewsPk.builder()
                        .destination(destination)
                        .user(userMapper.dtoToEntity(userDto))
                        .build()
                ).note(note)
                .build()
        );

        return ReviewDto.builder()
                .note(review.getNote())
                .destinationId(review.getId().getDestination().getId())
                .userId(review.getId().getUser().getId())
                .build();
    }

    private Destination findDestinationOrThrowNotFound(Integer destinationId){
        try {
            Destination destination = destinationRepository.findById(destinationId).get();
            return  destination;
        } catch (Exception e){
            throw new NotFoundException("Destination not found");
        }
    }
}
