package com.example.game_library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.game.id = ?1")
    List<Review> findByGameId(Long gameId);

    @Query("SELECT r FROM Review r WHERE r.author LIKE %?1%")
    List<Review> findByAuthor(String name);
}
