package project.game.pixel.service;

import org.springframework.http.ResponseEntity;
import project.game.pixel.dto.request.auth.IdCheckRequestDto;
import project.game.pixel.dto.request.auth.LoginRequestDto;
import project.game.pixel.dto.request.auth.SignupRequestDto;
import project.game.pixel.dto.response.auth.IdCheckResponseDto;
import project.game.pixel.dto.response.auth.SignInResponseDto;
import project.game.pixel.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUp (SignupRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn (LoginRequestDto dto);
    ResponseEntity<? super IdCheckResponseDto> logout (IdCheckRequestDto dto);
}
