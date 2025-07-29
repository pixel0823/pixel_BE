package project.game.pixel.service.implement;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.game.pixel.dto.request.auth.IdCheckRequestDto;
import project.game.pixel.dto.request.auth.LoginRequestDto;
import project.game.pixel.dto.request.auth.SignupRequestDto;
import project.game.pixel.dto.response.ResponseDto;
import project.game.pixel.dto.response.auth.IdCheckResponseDto;
import project.game.pixel.dto.response.auth.SignInResponseDto;
import project.game.pixel.dto.response.auth.SignUpResponseDto;
import project.game.pixel.entity.User;
import project.game.pixel.provider.JwtTokenProvider;
import project.game.pixel.repository.RefreshTokenRepository;
import project.game.pixel.repository.UserRepository;
import project.game.pixel.service.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try {
            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return IdCheckResponseDto.duplicateId();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignupRequestDto dto) {
        try {
            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return SignUpResponseDto.duplicateId();

            String email = dto.getEmail();
            String nickname = dto.getNickname();
            String name = dto.getUserName();
            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);
            String profileImageUrl = dto.getProfileImageUrl();

            User user = new User(dto);
            userRepository.save(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(LoginRequestDto dto) {

        try {
            String userId = dto.getUserId();
            Optional<User> userOptional = userRepository.findByUserId(userId);
            if (userOptional.isEmpty()) return SignInResponseDto.signInFail();

            User user = userOptional.get();
            String password = dto.getPassword();
            String encodedPassword = user.getPassword();

            boolean isMatch = passwordEncoder.matches(password, encodedPassword);
            if (!isMatch) return SignInResponseDto.signInFail();


            String accessToken = jwtTokenProvider.createAccessToken(userId);
            jwtTokenProvider.createRefreshToken(user.getNumberId());

            long expirationTime = jwtTokenProvider.getAccessTokenValidityInSeconds();

            return SignInResponseDto.success(accessToken, expirationTime);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<? super IdCheckResponseDto> logout(IdCheckRequestDto dto) {
        try {
            Optional<User> userOptional = userRepository.findByUserId(dto.getId());
            if (userOptional.isEmpty()) return ResponseDto.userNotFound();

            Long UserDbId = userOptional.get().getNumberId();
            refreshTokenRepository.deleteByUserId(UserDbId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IdCheckResponseDto.success();
    }


}
