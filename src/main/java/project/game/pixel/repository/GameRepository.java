package project.game.pixel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.game.pixel.entity.GameRoom;

public interface GameRepository extends JpaRepository<GameRoom, Integer> {

}
