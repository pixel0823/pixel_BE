package project.game.pixel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.game.pixel.dto.request.SignupRequestDto;
import project.game.pixel.dto.request.LoginRequestDto;
import project.game.pixel.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto requestDto) {
        return userService.login(requestDto);
    }
}
