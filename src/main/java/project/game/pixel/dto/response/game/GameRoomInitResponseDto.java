package project.game.pixel.dto.response.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.game.pixel.common.ResponseCode;
import project.game.pixel.common.ResponseMessage;
import project.game.pixel.dto.response.ResponseDto;

public class GameRoomInitResponseDto extends ResponseDto {
    private GameRoomInitResponseDto() {
        super();
    }

    public static ResponseEntity<GameRoomInitResponseDto> success() {
        GameRoomInitResponseDto responseBody = new GameRoomInitResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> fail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_NAME, ResponseMessage.DUPLICATE_NAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
