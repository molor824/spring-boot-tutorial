package com.example.game_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/reviews")
    public List<Review> getReviews(@RequestParam("author") Optional<String> authorParam) {
        return authorParam.map(author -> reviewRepository.findByAuthor(author)).orElse(reviewRepository.findAll());
    }

    @GetMapping("/reviews/{reviewId}")
    public Optional<Review> getReview(@PathVariable long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    @GetMapping("/reviews/game/{gameId}")
    public List<Review> getReviewsOfGame(@PathVariable long gameId) {
        return reviewRepository.findByGameId(gameId);
    }

    @PostMapping("/reviews")
    public Review addReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public void deleteReview(@PathVariable long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}