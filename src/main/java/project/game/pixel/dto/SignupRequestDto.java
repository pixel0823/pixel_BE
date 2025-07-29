package project.game.pixel.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String userId;       // 로그인 아이디
    private String password;     // 비밀번호 (최소 8자 이상 등 유효성 체크 권장)
    private String nickname;     // 게임용 닉네임
    private String userName;     // 실제이름 또는 사용자명 (선택)
    private String userImg;      // 프로필 이미지 URL (선택)
    private String email;        // 이메일 (선택)
}
