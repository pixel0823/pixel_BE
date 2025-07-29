package project.game.pixel.service;

import org.springframework.http.ResponseEntity;
import project.game.pixel.dto.SignupRequestDto;
import project.game.pixel.dto.LoginRequestDto;
import project.game.pixel.dto.UserInfoDto;
import project.game.pixel.dto.UserUpdateDto;

public interface UserService {
    // 회원가입
    ResponseEntity<? super SignupResponseDto> signup(SignupRequestDto dto);
    // 로그인
    ResponseEntity<? super LoginResqonseDto> login(LoginRequestDto dto);

    // 내 정보 조회
    ResponseEntity<? super UserInfoDto> getUserInfo(String userId);
    // 내 정보 수정
    ResponseEntity<Void> updateUserInfo(String userId, UserUpdateDto updateDto);
}

