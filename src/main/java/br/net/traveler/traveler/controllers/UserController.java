package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.request.UserDeletionRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import br.net.traveler.traveler.services.JwtService;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user-events")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;

    @Autowired
    UserMapper userMapper;

    @PostMapping
    public ResponseEntity createUser(
            @RequestBody UserRegistrationRequest body
    ){
        UserDto dto = userService.createUser(body.getId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping()
    public ResponseEntity deleteUser(
            @RequestBody UserDeletionRequest body
    ){
        userService.deleteUser(body.getId());
        return ResponseEntity.noContent().build();
    }
}
