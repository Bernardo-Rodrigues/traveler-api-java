package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.request.UserAuthenticationRequest;
import br.net.traveler.traveler.domain.request.UserRegistrationRequest;
import br.net.traveler.traveler.domain.request.UserUpdateRequest;
import br.net.traveler.traveler.domain.response.UserAuthenticationResponse;
import br.net.traveler.traveler.domain.response.UserRegistrationResponse;
import br.net.traveler.traveler.domain.response.UserSearchResponse;
import br.net.traveler.traveler.domain.response.UserUpdateResponse;
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

    @PostMapping
    public ResponseEntity<UserRegistrationResponse> createUser(
            @RequestBody UserRegistrationRequest requestBody
    ){
        UserDto dto = userService.createUser(userMapper.registrationRequestToDto(requestBody));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(UserRegistrationResponse.builder().uri(uri).build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserUpdateResponse> updateUser(
            @RequestBody UserUpdateRequest requestBody,
            @PathVariable Integer id
    ){
        UserDto dto = userService.updateUser(userMapper.updateRequestToDto(requestBody), id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.ok().body(UserUpdateResponse.builder().uri(uri).build());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserSearchResponse> getUser(
            @PathVariable Integer id
    ){
        UserDto dto = userService.findById(id);
        return ResponseEntity.ok().body(UserSearchResponse
                .builder()
                        .id(dto.getId())
                        .username(dto.getUsername())
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .avatarId(dto.getAvatarId())
                        .titleId(dto.getTitleId())
                .build()
        );
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
