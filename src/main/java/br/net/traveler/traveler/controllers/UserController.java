package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.request.UserAuthenticationRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import br.net.traveler.traveler.domain.response.UserAuthenticationResponse;
import br.net.traveler.traveler.domain.response.UserRegistrationResponse;
import br.net.traveler.traveler.services.JwtService;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(
            @RequestBody UserRegistrationRequest requestBody
    ){
        System.out.println("aqui");
        UserDto dto = userService.createUser(userMapper.registrationRequestToDto(requestBody));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserAuthenticationResponse> authenticateUser(
            @RequestBody UserAuthenticationRequest requestBody
    ){
        UserDto user = userService.identifyUser(userMapper.authenticationRequestToDto(requestBody));
        String jwt = jwtService.generateToken(user);
        return ResponseEntity.ok().body(UserAuthenticationResponse.builder().jwt(jwt).build());
    }
}
