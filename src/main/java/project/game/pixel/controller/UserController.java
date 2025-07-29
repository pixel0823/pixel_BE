package project.game.pixel.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.game.pixel.dto.request.user.UserDeleteRequestDto;
import project.game.pixel.dto.request.user.UserUpdateRequestDto;
import project.game.pixel.dto.response.user.UserDeleteResponseDto;
import project.game.pixel.dto.response.user.UserUpdateResponseDto;
import project.game.pixel.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/user-update")
    public ResponseEntity<? super UserUpdateResponseDto> updateUserInfo(
            @RequestBody @Valid UserUpdateRequestDto requestBody, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("유효성 오류: " + bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body("입력값 오류");
        }
        System.out.println("");
        ResponseEntity<? super UserUpdateResponseDto> response = userService.updateUserInfo(requestBody);
        return response;
    }

    @DeleteMapping("/user-delete")
    public ResponseEntity<? super UserDeleteResponseDto> deleteUserInfo(
            @RequestBody @Valid UserDeleteRequestDto requestBody
    ) {
        ResponseEntity<? super UserDeleteResponseDto> response = userService.deleteUser(requestBody);
        return response;
    }

}
