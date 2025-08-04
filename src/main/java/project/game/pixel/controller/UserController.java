package project.game.pixel.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.game.pixel.dto.request.user.UserUpdateRequestDto;
import project.game.pixel.dto.response.user.UserDeleteResponseDto;
import project.game.pixel.dto.response.user.UserInfoResponseDto;
import project.game.pixel.dto.response.user.UserUpdateResponseDto;
import project.game.pixel.provider.JwtTokenProvider;
import project.game.pixel.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/user-info")
    public ResponseEntity<? super UserInfoResponseDto> getUserInfo(
            @RequestHeader("Authorization") String authorization
    ) {
        String userId = jwtTokenProvider.extractUserIdFromHeader(authorization);
        ResponseEntity<? super UserInfoResponseDto> response = userService.getUserInfo(userId);
        return response;
    }

    @PutMapping("/user-update")
    public ResponseEntity<? super UserUpdateResponseDto> updateUserInfo(
            @RequestHeader("Authorization") String authorization,
            @RequestBody @Valid UserUpdateRequestDto requestBody
    ) {
        String userId = jwtTokenProvider.extractUserIdFromHeader(authorization);
        requestBody.setUserId(userId);
        ResponseEntity<? super UserUpdateResponseDto> response = userService.updateUserInfo(requestBody);
        return response;
    }

    @DeleteMapping("/user-delete")
    public ResponseEntity<? super UserDeleteResponseDto> deleteUserInfo(
            @RequestHeader("Authorization") String authorization
    ) {
        String userId = jwtTokenProvider.extractUserIdFromHeader(authorization);
        ResponseEntity<? super UserDeleteResponseDto> response = userService.deleteUser(userId);
        return response;
    }

}
