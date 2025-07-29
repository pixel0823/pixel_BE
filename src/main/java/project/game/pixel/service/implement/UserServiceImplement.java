package project.game.pixel.service.implement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.game.pixel.dto.request.user.UserDeleteRequestDto;
import project.game.pixel.dto.request.user.UserUpdateRequestDto;
import project.game.pixel.dto.response.ResponseDto;
import project.game.pixel.dto.response.user.UserDeleteResponseDto;
import project.game.pixel.dto.response.user.UserUpdateResponseDto;
import project.game.pixel.entity.User;
import project.game.pixel.provider.JwtTokenProvider;
import project.game.pixel.repository.UserRepository;
import project.game.pixel.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImplement  implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<? super UserUpdateResponseDto> updateUserInfo(UserUpdateRequestDto dto) {
        try {
            String userId = dto.getUserId();
            Optional<User> userOptional = userRepository.findByUserId(userId);
            if (userOptional.isEmpty()) return UserUpdateResponseDto.fail();

            User user = userOptional.get();

            if(dto.getPassword() != null) {
                String password = dto.getPassword();
                String encodedPassword = passwordEncoder.encode(password);
                user.setPassword(encodedPassword);
            }
            if(dto.getUserName() != null) user.setName(dto.getUserName());
            if(dto.getEmail() != null) user.setEmail(dto.getEmail());
            if(dto.getNickname() != null) user.setNickname(dto.getNickname());
            if(dto.getProfileImageUrl() != null) user.setProfileImageUrl(dto.getProfileImageUrl());

            userRepository.save(user);
            System.out.println("user");

            String accessToken = jwtTokenProvider.createAccessToken(userId);
            jwtTokenProvider.createRefreshToken(user.getNumberId());
            long expirationTime = jwtTokenProvider.getAccessTokenValidityInSeconds();

            return UserUpdateResponseDto.success(accessToken, expirationTime);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
    @Override
    public ResponseEntity<? super UserDeleteResponseDto> deleteUser(UserDeleteRequestDto dto) {
        try {
            String userId = dto.getUserId();
            Optional<User> userOptional = userRepository.findByUserId(userId);
            if (userOptional.isEmpty()) return UserDeleteResponseDto.fail();

            User user = userOptional.get();
            userRepository.deleteById(user.getNumberId());

            return UserDeleteResponseDto.success();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

}
