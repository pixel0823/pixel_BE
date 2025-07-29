package project.game.pixel.service;

import project.game.pixel.dto.SignupRequestDto;
import project.game.pixel.dto.LoginRequestDto;

public interface UserService {
    void signup(SignupRequestDto requestDto);
    String login(LoginRequestDto requestDto);
    // 필요하다면 사용자 조회 등 추가 가능
}

