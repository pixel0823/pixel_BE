package project.game.pixel.dto.response.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.game.pixel.common.ResponseCode;
import project.game.pixel.common.ResponseMessage;
import project.game.pixel.dto.response.ResponseDto;

@Getter
public class UserDeleteResponseDto extends ResponseDto {

    private UserDeleteResponseDto() {
        super();
    }

    public static ResponseEntity<UserDeleteResponseDto> success() {
        UserDeleteResponseDto responseBody = new UserDeleteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> fail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
