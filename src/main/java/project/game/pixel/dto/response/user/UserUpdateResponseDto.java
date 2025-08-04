package project.game.pixel.dto.response.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.game.pixel.common.ResponseCode;
import project.game.pixel.common.ResponseMessage;
import project.game.pixel.dto.response.ResponseDto;

@Getter
public class UserUpdateResponseDto extends ResponseDto {

    private String token;
    private Long expirationTime;

    private UserUpdateResponseDto(String token, Long expirationTime) {
        super();
        this.token = token;
        this.expirationTime = expirationTime;
    }

    public static ResponseEntity<UserUpdateResponseDto> success(String token, Long expirationTime) {
        UserUpdateResponseDto responseBody = new UserUpdateResponseDto(token, expirationTime);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> fail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}

