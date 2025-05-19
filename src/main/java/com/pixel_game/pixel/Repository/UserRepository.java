package com.pixel_game.pixel.Repository;

import com.pixel_game.pixel.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);
    Optional<User> findByUserId(String userId);
}