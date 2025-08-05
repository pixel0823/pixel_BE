package project.game.pixel.service;

import org.springframework.http.ResponseEntity;
import project.game.pixel.dto.request.game.GameRoomInitRequestDto;
import project.game.pixel.dto.response.game.GameRoomInitResponseDto;

public interface GameService {
    ResponseEntity<? super GameRoomInitResponseDto> initGameRoom(GameRoomInitRequestDto dto);
}
