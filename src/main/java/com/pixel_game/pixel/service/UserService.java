package com.pixel_game.pixel.service;

import com.pixel_game.pixel.DTO.JoinRequest;
import com.pixel_game.pixel.DTO.LoginRequest;
import com.pixel_game.pixel.Entity.User;
import com.pixel_game.pixel.Repository.UserRepository;
import com.pixel_game.pixel.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public void join(JoinRequest req) {
       if (!req.getPassword().equals(req.getPasswordCheck())) {
           throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
       }

       if (userRepository.existsByUserId(req.getUserId())) {
           throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
       }

       String encoderdPw = encoder.encode(req.getPassword());

       User user = new User();
       user.setUserId(req.getUserId());
       user.setPassword(encoderdPw);
       user.setEmail(req.getEmail());
       user.setName(req.getName());

       userRepository.save(user);
   }

   public String login(LoginRequest req) {
       User user = userRepository.findByUserId(req.getUserId())
               .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

       if (!encoder.matches(req.getPassword(), user.getPassword())) {
           throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
       }

//       if (user.getRole() == null) {
//           user.setRole(UserRole.USER); // 기본 역할 설정
//           userRepository.save(user);
//       }

       return jwtUtil.createToken(user.getUserId(), user.getRole());
   }


}