package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.*;
import br.net.traveler.traveler.services.ContinentService;
import br.net.traveler.traveler.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/continents")
public class ContinentController {

    @Autowired
    ContinentService service;

    @Autowired
    JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<ContinentDto>> list (@RequestHeader(value = "jwt") String jwt){
        jwtService.validateToken(jwt);
        List<ContinentDto> dtos = service.list();
        return ResponseEntity.ok().body(dtos);
    }
}
