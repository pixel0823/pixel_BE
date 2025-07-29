package project.game.pixel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.game.pixel.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
