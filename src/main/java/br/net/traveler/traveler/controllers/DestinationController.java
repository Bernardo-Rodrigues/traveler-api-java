package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.DestinationDto;
import br.net.traveler.traveler.domain.dto.DestinationWithScoreDto;
import br.net.traveler.traveler.domain.response.DestinationListResponse;
import br.net.traveler.traveler.services.DestinationService;
import com.azure.core.annotation.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
