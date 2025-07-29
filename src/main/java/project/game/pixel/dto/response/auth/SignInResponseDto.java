package project.game.pixel.dto.response.auth;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.game.pixel.common.ResponseCode;
import project.game.pixel.common.ResponseMessage;
import project.game.pixel.dto.response.ResponseDto;

@Getter
public class SignInResponseDto extends ResponseDto {

    private String token;
    private Long expirationTime;

    private SignInResponseDto (String token, long expirationTime) {
        super();
        this.token = token;
        this.expirationTime = expirationTime;
    }

    public static ResponseEntity<SignInResponseDto> success (String token, long expirationTime) {
        SignInResponseDto responseBody = new SignInResponseDto(token, expirationTime);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }



}
