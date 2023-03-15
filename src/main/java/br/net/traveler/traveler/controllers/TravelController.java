package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.TravelDto;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.mapper.TravelMapper;
import br.net.traveler.traveler.domain.request.AddTravelRequest;
import br.net.traveler.traveler.domain.response.CreateTravelResponse;
import br.net.traveler.traveler.services.JwtService;
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

    @Autowired
    JwtService jwtService;

    @GetMapping("/current")
    public ResponseEntity<TravelDto> getCurrentTrip (@RequestHeader(value = "jwt") String jwt){
        UserDto authenticatedUser = jwtService.validateToken(jwt);
        TravelDto dto = service.getCurrentTrip(authenticatedUser.getId());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<TravelDto>> listUpcomingTrips (@RequestHeader(value = "jwt") String jwt){
        UserDto authenticatedUser = jwtService.validateToken(jwt);
        List<TravelDto> dtos = service.listUpcomingTrips(authenticatedUser.getId());
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping("")
    public ResponseEntity<CreateTravelResponse> createTrip (
            @RequestHeader(value = "jwt") String jwt,
            @RequestBody AddTravelRequest requestBody
    ){
        UserDto authenticatedUser = jwtService.validateToken(jwt);
        TravelDto dto = mapper.createRequestToDto(requestBody);
        dto.setUserId(authenticatedUser.getId());
        dto = service.createTravel(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(CreateTravelResponse.builder().uri(uri).build());
    }
}
