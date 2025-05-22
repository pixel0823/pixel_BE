package com.pixel_game.pixel.DTO.Response.auth;

import com.pixel_game.pixel.DTO.Response.ResponseDTO;
import com.pixel_game.pixel.common.ResponseCode;
import com.pixel_game.pixel.common.ResponseMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.Getter;

@Getter
public class JoinResponseDTO extends ResponseDTO {

    private JoinResponseDTO() {
        super();
    }

    public static ResponseEntity<JoinResponseDTO> success () {
        JoinResponseDTO responseBody = new JoinResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDTO> duplicateId () {
        ResponseDTO responseBody = new ResponseDTO(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDTO> passwordNotMatch () {
        ResponseDTO responseBody = new ResponseDTO(ResponseCode.PASSWORD_NOT_MATCH, ResponseMessage.PASSWORD_NOT_MATCH);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }



}
