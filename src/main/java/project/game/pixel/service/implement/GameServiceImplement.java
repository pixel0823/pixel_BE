package project.game.pixel.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.game.pixel.dto.request.game.GameRoomInitRequestDto;
import project.game.pixel.dto.response.ResponseDto;
import project.game.pixel.dto.response.game.GameRoomInitResponseDto;
import project.game.pixel.entity.GameRoom;
import project.game.pixel.repository.GameRepository;
import project.game.pixel.repository.UserRepository;
import project.game.pixel.service.GameService;

@Service
@RequiredArgsConstructor
public class GameServiceImplement implements GameService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Override
    public ResponseEntity<? super GameRoomInitResponseDto> initGameRoom(GameRoomInitRequestDto dto) {
        try {
            GameRoom room = new GameRoom(dto);
            gameRepository.save(room);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GameRoomInitResponseDto.success();
    }
}
