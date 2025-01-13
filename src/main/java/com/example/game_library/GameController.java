package com.example.game_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping(path = "/games")
    public List<Game> getGames(@RequestParam("title") Optional<String> titleParam) {
        return titleParam.map(param -> gameRepository.findByTitle(param))
                .orElse(gameRepository.findAll());
    }

    @GetMapping(path = "/games/{gameId}")
    public Optional<Game> getGame(@PathVariable("gameId") Long gameId) {
        return gameRepository.findById(gameId);
    }

    @PostMapping(path = "/games")
    public Game addGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @DeleteMapping(path = "/games/{gameId}")
    public void deleteGame(@PathVariable("gameId") Long gameId) {
        gameRepository.deleteById(gameId);
    }
}