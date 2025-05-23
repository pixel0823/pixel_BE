package com.pixel_game.pixel.DTO.Response;

import com.pixel_game.pixel.common.ResponseCode;
import com.pixel_game.pixel.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDTO {
    private String code;
    private String message;

    public ResponseDTO() {
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
    }
    public static ResponseEntity<ResponseDTO> databaseError() {
        ResponseDTO responseBody = new ResponseDTO(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<ResponseDTO> validationFail() {
        ResponseDTO responseBody = new ResponseDTO(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
