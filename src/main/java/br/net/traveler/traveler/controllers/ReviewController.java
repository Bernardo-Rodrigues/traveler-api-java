package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.ReviewDto;
import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.request.AddReviewRequest;
import br.net.traveler.traveler.services.JwtService;
import br.net.traveler.traveler.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService service;

    @Autowired
    JwtService jwtService;

    @PostMapping("/destinations/{id}")
    public ResponseEntity<ReviewDto> createReview (
            @RequestHeader(value = "jwt") String jwt,
            @PathVariable(value = "id") Integer destinationId,
            @RequestBody AddReviewRequest requestBody
    ){
        UserDto authenticatedUser = jwtService.validateToken(jwt);
        ReviewDto dto = service.createReview(authenticatedUser, destinationId, requestBody.getNote());
        return ResponseEntity.ok().body(dto);
    }
}
