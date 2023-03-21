package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.services.AchievementService;
import br.net.traveler.traveler.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/destinations/{id}")
    public ResponseEntity<List<AchievementDto>> get (
            @RequestHeader(value = "jwt") String jwt,
            @PathVariable Integer id
    ){
        UserDto authenticatedUser = jwtService.validateToken(jwt);
        List<AchievementDto> dtos = service.get(authenticatedUser, id);
        return ResponseEntity.ok().body(dtos);
    }
}
