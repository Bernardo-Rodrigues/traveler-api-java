package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.*;
import br.net.traveler.traveler.services.DestinationService;
import br.net.traveler.traveler.services.JwtService;
import com.azure.core.annotation.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    DestinationService service;

    @Autowired
    JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<DestinationDto>> list (
            @RequestHeader(value = "jwt") String jwt,
            @QueryParam("name") String name
    ){
        jwtService.validateToken(jwt);
        List<DestinationDto> dtos = service.list(name);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/top")
    public ResponseEntity<List<DestinationWithScoreDto>> listTop (
            @RequestHeader(value = "jwt") String jwt,
            @QueryParam("continentName") String continentName
    ){

        jwtService.validateToken(jwt);
        List<DestinationWithScoreDto> dtos = service.listTop(continentName);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<FavoriteDestinationWithScoreDto>> listFavorites (@RequestHeader(value = "jwt") String jwt){
        UserDto authenticatedUser = jwtService.validateToken(jwt);
        List<FavoriteDestinationWithScoreDto> dtos = service.listFavorites(authenticatedUser.getId());
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationInformationsDto> find (
            @RequestHeader(value = "jwt") String jwt,
            @PathVariable Integer id
    ){
        UserDto authenticatedUser = jwtService.validateToken(jwt);
        DestinationInformationsDto dto = service.find(authenticatedUser.getId(), id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}/tips")
    public ResponseEntity<List<TipDto>> listTips (
            @RequestHeader(value = "jwt") String jwt,
            @PathVariable Integer id
    ){
        jwtService.validateToken(jwt);
        List<TipDto> tips = service.listTips(id);
        return ResponseEntity.ok().body(tips);
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<Void> favorite(
            @RequestHeader(value = "jwt") String jwt,
            @PathVariable(value = "id") Integer destinationId
    ){
        UserDto authenticatedUser = jwtService.validateToken(jwt);
        service.favorite(authenticatedUser, destinationId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/unfavorite")
    public ResponseEntity<Void> unfavorite(
            @RequestHeader(value = "jwt") String jwt,
            @PathVariable(value = "id") Integer destinationId
    ){
        UserDto authenticatedUser = jwtService.validateToken(jwt);
        service.unfavorite(authenticatedUser.getId(), destinationId);
        return ResponseEntity.noContent().build();
    }
}
