package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.TravelDto;
import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.exception.BadRequestException;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mapper.TravelMapper;
import br.net.traveler.traveler.repositories.AchievementUserRepository;
import br.net.traveler.traveler.repositories.DestinationRepository;
import br.net.traveler.traveler.repositories.TravelRepository;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.AchievementService;
import br.net.traveler.traveler.services.TravelService;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TravelRepository travelRepository;
    @Autowired
    DestinationRepository destinationRepository;
    @Autowired
    TravelMapper travelMapper;

    @Override
    public TravelDto getCurrentTrip(Integer userId) {
        Date now = new Date();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);

        Travel travel = travelRepository.findCurrentTrip(userId, now,  yesterday.getTime());

        return TravelDto.builder()
                .startDate(travel.getStartDate())
                .endDate(travel.getEndDate())
                .destinationId(travel.getDestination().getId())
                .build();
    }

    @Override
    public List<TravelDto> listUpcomingTrips(Integer userId) {
        List<Travel> trips = travelRepository.listUpcomingTrips(userId);
        List<TravelDto> dtos = trips.stream().map((trip) ->
                TravelDto.builder()
                        .id(trip.getId())
                        .userId(trip.getUser().getId())
                        .destinationId(trip.getDestination().getId())
                        .startDate(trip.getStartDate())
                        .endDate(trip.getEndDate())
                        .build()
        ).toList();

        return dtos;
    }

    @Override
    public TravelDto createTravel(TravelDto dto) {
        findDestinationOrThrowNotFound(dto.getDestinationId());

        boolean validDates = this.checkDates(dto.getStartDate(), dto.getEndDate());
        if (!validDates) throw new BadRequestException("Dates are invalid");

        checkTripsConflict(dto.getUserId(), dto.getStartDate(), dto.getEndDate());

        return travelMapper.entityToDto(travelRepository.save(travelMapper.dtoToEntity(dto)));
    }

    private boolean checkDates(Date startDate, Date endDate) {
        return !(
                (startDate.toInstant().isAfter(endDate.toInstant())) ||
                 Instant.now().minus(1, ChronoUnit.DAYS).isAfter(endDate.toInstant())
        );
    }

    private void checkTripsConflict(Integer userId, Date startdDate, Date endDate) {
    Travel haveConflict = travelRepository.findByDate(
                userId,
                startdDate,
                endDate
        );
    if (haveConflict != null) throw new BadRequestException("Conflict with dates of different trips");
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
