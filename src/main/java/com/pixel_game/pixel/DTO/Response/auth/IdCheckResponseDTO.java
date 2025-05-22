package com.pixel_game.pixel.DTO.Response.auth;

import com.pixel_game.pixel.DTO.Response.ResponseDTO;
import com.pixel_game.pixel.common.ResponseCode;
import com.pixel_game.pixel.common.ResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class IdCheckResponseDTO extends ResponseDTO {

    private IdCheckResponseDTO() {
        super();
    }

    public static ResponseEntity<IdCheckResponseDTO> success() {
        IdCheckResponseDTO responseBody = new IdCheckResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public  static ResponseEntity<ResponseDTO> duplicateId() {
        ResponseDTO responseBody = new ResponseDTO(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
