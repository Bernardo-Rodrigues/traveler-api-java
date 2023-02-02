package br.net.traveler.traveler.controllers;

import br.net.traveler.traveler.domain.dto.AchievementDto;
import br.net.traveler.traveler.domain.dto.ReviewDto;
import br.net.traveler.traveler.domain.request.AddReviewRequest;
import br.net.traveler.traveler.services.AchievementService;
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

    @PostMapping("/destinations/{id}")
    public ResponseEntity<ReviewDto> createReview (
            @RequestHeader(value = "user-id") Integer userId,
            @PathVariable(value = "id") Integer destinationId,
            @RequestBody AddReviewRequest requestBody
    ){
        ReviewDto dto = service.createReview(userId, destinationId, requestBody.getNote());
        return ResponseEntity.ok().body(dto);
    }
}
