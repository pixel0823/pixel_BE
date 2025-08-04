package project.game.pixel.service;

import org.springframework.http.ResponseEntity;
import project.game.pixel.dto.request.user.UserDeleteRequestDto;
import project.game.pixel.dto.request.user.UserUpdateRequestDto;
import project.game.pixel.dto.response.user.UserDeleteResponseDto;
import project.game.pixel.dto.response.user.UserUpdateResponseDto;

public interface UserService {
    ResponseEntity<? super UserUpdateResponseDto> updateUserInfo(UserUpdateRequestDto dto);
    ResponseEntity<? super UserDeleteResponseDto> deleteUser(UserDeleteRequestDto dto);
}
