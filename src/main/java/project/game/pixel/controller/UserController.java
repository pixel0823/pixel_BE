package project.game.pixel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import project.game.pixel.dto.UserInfoDto;
import project.game.pixel.dto.UserUpdateDto;
import project.game.pixel.service.UserService;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<? super UserInfoDto> getMyInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUserInfo(userDetails.getUsername());
    }

    @PutMapping("/me")
    public ResponseEntity<Void> updateMyInfo(@RequestBody UserUpdateDto dto, @AuthenticationPrincipal UserDetails userDetails) {
        return userService.updateUserInfo(userDetails.getUsername(), dto);
    }

    // 필요 시 회원 탈퇴, 비밀번호 변경 등 추가
}
