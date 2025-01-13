package com.example.game_library;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @QueryMapping
    public Optional<Game> gameById(@Argument Long id) {
        return gameRepository.findById(id);
    }

    @QueryMapping
    public List<Game> gamesByTitle(@Argument String title) {
        return gameRepository.findByTitle(title);
    }

    @QueryMapping
    public Optional<Review> reviewById(@Argument Long id) {
        return reviewRepository.findById(id);
    }

    @QueryMapping
    public List<Review> reviewsByGameId(@Argument Long id) {
        return reviewRepository.findByGameId(id);
    }

    @QueryMapping
    public List<Review> reviewsByAuthor(@Argument String name) {
        return reviewRepository.findByAuthor(name);
    }
}
