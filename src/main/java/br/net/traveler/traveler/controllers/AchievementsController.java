package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.ContinentDto;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.services.AchievementService;
import br.net.traveler.traveler.services.ContinentService;
import br.net.traveler.traveler.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/achievements")
public class AchievementsController {

    @Autowired
    AchievementService service;

    @Autowired
    JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<AchievementDto>> listByUser (@RequestHeader(value = "jwt") String jwt){
        UserDto authenticatedUser = jwtService.validateToken(jwt);
        List<AchievementDto> dtos = service.listByUser(authenticatedUser.getId());
        return ResponseEntity.ok().body(dtos);
    }
}
