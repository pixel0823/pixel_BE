package com.pixel_game.pixel.DTO.Response.auth;

import com.pixel_game.pixel.DTO.Response.ResponseDTO;
import com.pixel_game.pixel.common.ResponseCode;
import com.pixel_game.pixel.common.ResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class LoginResponseDTO extends ResponseDTO {
    private String token;
    private int expirationTime;

    private LoginResponseDTO (String token) {
        super();
        this.token = token;
        this.expirationTime = 1000;
    }

    public static ResponseEntity<LoginResponseDTO> success (String token) {
        LoginResponseDTO responseBody = new LoginResponseDTO(token);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDTO> loginFail() {
        ResponseDTO responseBody = new ResponseDTO(ResponseCode.LOGIN_FAIL, ResponseMessage.LOGIN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
