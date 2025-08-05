package project.game.pixel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.game.pixel.dto.request.game.GameRoomInitRequestDto;
import project.game.pixel.dto.response.game.GameRoomInitResponseDto;
import project.game.pixel.provider.JwtTokenProvider;
import project.game.pixel.service.GameService;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/game-init")
    public ResponseEntity<? super GameRoomInitResponseDto> initGameRoom(
            @RequestHeader("Authorization") String authorization,
            @RequestBody GameRoomInitRequestDto requestBody) {
        String ownerId = jwtTokenProvider.extractUserIdFromHeader(authorization);
        requestBody.setOwnerId(ownerId);
        ResponseEntity<? super GameRoomInitResponseDto> response = gameService.initGameRoom(requestBody);
        return response;
    }
}
