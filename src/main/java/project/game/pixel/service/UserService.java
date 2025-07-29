package project.game.pixel.service;

import org.springframework.http.ResponseEntity;
import project.game.pixel.dto.request.SignupRequestDto;
import project.game.pixel.dto.request.LoginRequestDto;

public interface UserService {
    void signup(SignupRequestDto requestDto);
    String login(LoginRequestDto requestDto);

    ResponseEntity<? super SignupResponseDto> signup (SignupRequestDto dto);
    // 필요하다면 사용자 조회 등 추가 가능
}

