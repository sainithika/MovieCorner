package dev.nithika.movies.controller;

import dev.nithika.movies.service.ReviewService;
import dev.nithika.movies.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Reviews> createReviews(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Reviews>(reviewService.createReviewBy(payload.get("reviewBody"),payload.get("imdbId")), HttpStatus.CREATED);
    }
}
