package com.pixel_game.pixel.service.implement;


import com.pixel_game.pixel.DTO.Request.auth.IdCheckRequest;
import com.pixel_game.pixel.DTO.Request.auth.JoinRequest;
import com.pixel_game.pixel.DTO.Request.auth.LoginRequest;
import com.pixel_game.pixel.DTO.Response.ResponseDTO;
import com.pixel_game.pixel.DTO.Response.auth.IdCheckResponseDTO;
import com.pixel_game.pixel.DTO.Response.auth.JoinResponseDTO;
import com.pixel_game.pixel.DTO.Response.auth.LoginResponseDTO;
import com.pixel_game.pixel.Entity.User;
import com.pixel_game.pixel.Entity.UserRole;
import com.pixel_game.pixel.Repository.UserRepository;
import com.pixel_game.pixel.provider.JwtProvider;
import com.pixel_game.pixel.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super IdCheckResponseDTO> idCheck(IdCheckRequest dto) {
        try {
            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return IdCheckResponseDTO.duplicateId();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }

        return IdCheckResponseDTO.success();
    }

    @Override
    public ResponseEntity<? super JoinResponseDTO> Join(JoinRequest dto) {

        try {
            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return JoinResponseDTO.duplicateId();

            if (!dto.getPassword().equals(dto.getPasswordCheck())) {
                return JoinResponseDTO.passwordNotMatch();
            }

            String encoderdPw = passwordEncoder.encode(dto.getPassword());

            User user = dto.toEntity(encoderdPw);
            user.setRole(UserRole.USER);

            userRepository.save(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }

        return JoinResponseDTO.success();
    }

    @Override
    public ResponseEntity<? super LoginResponseDTO> Login(LoginRequest dto) {

        String token = null;

        try {
            String userId = dto.getUserId();
            User userEntity = userRepository.findByUserId(userId)
                    .orElse(null);
            if (userEntity == null) return LoginResponseDTO.loginFail();


            if (!passwordEncoder.matches(dto.getPassword(), userEntity.getPassword())) {
                return LoginResponseDTO.loginFail();
            }

            UserRole role = userEntity.getRole();
            token = jwtProvider.createToken(userId, role);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return LoginResponseDTO.success(token);
    }
}
