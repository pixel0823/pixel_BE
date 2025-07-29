package project.game.pixel.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.game.pixel.dto.request.auth.IdCheckRequestDto;
import project.game.pixel.dto.request.auth.SignupRequestDto;
import project.game.pixel.dto.request.auth.LoginRequestDto;
import project.game.pixel.dto.response.auth.IdCheckResponseDto;
import project.game.pixel.dto.response.auth.SignInResponseDto;
import project.game.pixel.dto.response.auth.SignUpResponseDto;
import project.game.pixel.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck (
            @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp (
            @RequestBody @Valid SignupRequestDto requestBody, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("유효성 오류: " + bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body("입력값 오류");
        }

        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn (
            @RequestBody @Valid LoginRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @PostMapping("/logout")
    public ResponseEntity<? super IdCheckResponseDto> logout(
            @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<? super IdCheckResponseDto> response = authService.logout(requestBody);
        return response;
    }
}
