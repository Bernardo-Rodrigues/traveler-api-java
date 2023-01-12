package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.request.UserAuthenticationRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import br.net.traveler.traveler.domain.response.UserAuthenticationResponse;
import br.net.traveler.traveler.domain.response.UserRegistrationResponse;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(
            @RequestBody UserRegistrationRequest requestBody
    ){
        UserDto dto = userService.createUser(userMapper.registrationRequestToDto(requestBody));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(userMapper.dtoToRegistrationResponse(dto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserAuthenticationResponse> authenticateUser(
            @RequestBody UserAuthenticationRequest requestBody
    ){
        String jwt = userService.identifyUser(userMapper.authenticationRequestToDto(requestBody));
        return ResponseEntity.ok().body(UserAuthenticationResponse.builder().jwt(jwt).build());
    }
}
