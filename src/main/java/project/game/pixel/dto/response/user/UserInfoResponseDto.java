package project.game.pixel.dto.response.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.game.pixel.common.ResponseCode;
import project.game.pixel.common.ResponseMessage;
import project.game.pixel.dto.response.ResponseDto;
import project.game.pixel.entity.User;

@Getter
public class UserInfoResponseDto extends ResponseDto {
    private String userId;
    private String email;
    private String nickname;
    private String profileImageUrl;

    private UserInfoResponseDto(User user) {
        super();
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
    }

    public static ResponseEntity<UserInfoResponseDto> success(User user) {
        UserInfoResponseDto responseBody = new UserInfoResponseDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> fail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.USER_NOT_FOUND, ResponseMessage.USER_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
