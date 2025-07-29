package project.game.pixel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.game.pixel.dto.request.SignupRequestDto;
import project.game.pixel.dto.request.LoginRequestDto;
import project.game.pixel.repository.UserRepository;
import project.game.pixel.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void signup(SignupRequestDto requestDto) {
        // 중복 검사, 비밀번호 암호화, 저장 등 로직 작성
    }

    @Override
    public String login(LoginRequestDto requestDto) {
        // 인증 처리 후, JWT 토큰 반환 등 로직 작성
        return null;
    }
}
