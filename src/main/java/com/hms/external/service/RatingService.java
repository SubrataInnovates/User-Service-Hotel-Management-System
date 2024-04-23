package com.hms.external.service;


import com.hms.entity.Rating;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService
{
    //get single rating

    @GetMapping("/ratings/{ratingId}")
    public Rating getRating(@PathVariable String ratingId);

    @GetMapping("/ratings")
    public Rating getRatings();
    //post
    @PostMapping("/ratings")
    public Rating createRating(Rating values);

    //put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId, Rating rating);

    //delete
    @DeleteMapping("/rating/{ratingId}")
    public void deleteMapping(@PathVariable String ratingId);
}
