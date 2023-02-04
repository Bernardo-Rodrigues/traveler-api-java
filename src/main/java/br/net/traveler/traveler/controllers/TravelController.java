package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.TravelDto;
import br.net.traveler.traveler.services.AchievementService;
import br.net.traveler.traveler.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    TravelService service;

    @GetMapping("/current")
    public ResponseEntity<TravelDto> getCurrentTrip (@RequestHeader(value = "user-id") Integer userId){
        TravelDto dto = service.getCurrentTrip(userId);
        return ResponseEntity.ok().body(dto);
    }
}
