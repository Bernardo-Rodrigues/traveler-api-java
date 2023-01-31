package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.*;
import br.net.traveler.traveler.domain.response.DestinationListResponse;
import br.net.traveler.traveler.services.DestinationService;
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

    @GetMapping
    public ResponseEntity<List<DestinationDto>> list (@QueryParam("name") String name){
        List<DestinationDto> dtos = service.list(name);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/top")
    public ResponseEntity<List<DestinationWithScoreDto>> listTop (@QueryParam("continentName") String continentName){
        List<DestinationWithScoreDto> dtos = service.listTop(continentName);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<FavoriteDestinationWithScoreDto>> listFavorites (@RequestHeader(value = "user-id") Integer userId){
        List<FavoriteDestinationWithScoreDto> dtos = service.listFavorites(userId);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationInformationsDto> find (
            @RequestHeader(value = "user-id") Integer userId,
            @PathVariable Integer id
    ){
        DestinationInformationsDto dto = service.find(userId, id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}/tips")
    public ResponseEntity<List<TipDto>> listTips (
            @PathVariable Integer id
    ){
        List<TipDto> tips = service.listTips(id);
        return ResponseEntity.ok().body(tips);
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<Void> favorite(
            @RequestHeader(value = "user-id") Integer userId,
            @PathVariable(value = "id") Integer destinationId
    ){
        service.favorite(userId, destinationId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/unfavorite")
    public ResponseEntity<Void> unfavorite(
            @RequestHeader(value = "user-id") Integer userId,
            @PathVariable(value = "id") Integer destinationId
    ){
        service.unfavorite(userId, destinationId);
        return ResponseEntity.noContent().build();
    }
}
