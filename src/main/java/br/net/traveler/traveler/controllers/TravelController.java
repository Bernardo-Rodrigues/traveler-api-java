package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.TravelDto;
import br.net.traveler.traveler.domain.mapper.TravelMapper;
import br.net.traveler.traveler.domain.request.AddTravelRequest;
import br.net.traveler.traveler.domain.response.CreateTravelResponse;
import br.net.traveler.traveler.services.AchievementService;
import br.net.traveler.traveler.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    TravelService service;
    @Autowired
    TravelMapper mapper;

    @GetMapping("/current")
    public ResponseEntity<TravelDto> getCurrentTrip (@RequestHeader(value = "user-id") Integer userId){
        TravelDto dto = service.getCurrentTrip(userId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("")
    public ResponseEntity<CreateTravelResponse> createTrip (
            @RequestHeader(value = "user-id") Integer userId,
            @RequestBody AddTravelRequest requestBody
    ){
        TravelDto dto = mapper.createRequestToDto(requestBody);
        dto.setUserId(userId);
        dto = service.createTravel(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(CreateTravelResponse.builder().uri(uri).build());
    }
}
