package project.game.pixel.mapper;

import project.game.pixel.dto.SignupRequestDto;
import project.game.pixel.entity.User;

public class UserMapper {
    // password는 암호화된 값이 이미 전달됨 (보안을 위해)
    public static User toEntity(SignupRequestDto dto, String encodedPassword) {
        return User.builder()
                .userId(dto.getUserId())
                .password(encodedPassword)
                .nickname(dto.getNickname())
                .userName(dto.getUserName())
                .userImg(dto.getUserImg())
                .email(dto.getEmail())
                .status("ACTIVE")
                .build();
    }
}
