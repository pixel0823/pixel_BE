package project.game.pixel.mapper;

import project.game.pixel.dto.SignupRequestDto;
import project.game.pixel.dto.UserInfoDto;
import project.game.pixel.dto.UserUpdateDto;
import project.game.pixel.entity.User;

public class UserMapper {
    // 회원가입 DTO -> 엔티티 변환
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

    // 엔티티 → 내 정보 응답 DTO 변환
    public static UserInfoDto toUserInfoDto(User user) {
        return UserInfoDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .userName(user.getUserName())
                .userImg(user.getUserImg())
                .email(user.getEmail())
                .build();
    }

    // 내 정보 수정 DTO → 엔티티 필드 업데이트 (setter 직접 호출)
    public static void updateUserFromDto(User user, UserUpdateDto dto) {
        if (dto.getNickname() != null) {
            user.setNickname(dto.getNickname());
        }
        if (dto.getUserName() != null) {
            user.setUserName(dto.getUserName());
        }
        if (dto.getUserImg() != null) {
            user.setUserImg(dto.getUserImg());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        // 필요하면 추가 필드 업데이트
    }
}
