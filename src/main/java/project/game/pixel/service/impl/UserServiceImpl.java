package project.game.pixel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.game.pixel.dto.SignupRequestDto;
import project.game.pixel.dto.LoginRequestDto;
import project.game.pixel.entity.User;
import project.game.pixel.exception.InvalidPasswordException;
import project.game.pixel.exception.UserAlreadyExistsException;
import project.game.pixel.exception.UserNotFoundException;
import project.game.pixel.mapper.UserMapper;
import project.game.pixel.repository.UserRepository;
import project.game.pixel.service.UserService;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void signup(SignupRequestDto requestDto) {
        if (userRepository.findByUserId(requestDto.getUserId()).isPresent()) {
            throw new UserAlreadyExistsException("이미 존재하는 사용자입니다.");
        }
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = UserMapper.toEntity(requestDto, encodedPassword);
        userRepository.save(user);
    }

    @Override
    public String login(LoginRequestDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("사용자가 존재하지 않습니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 올바르지 않습니다.");
        }
        return jwtTokenProvider.createToken(user.getUserId());
    }

}
