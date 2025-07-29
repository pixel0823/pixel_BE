package project.game.pixel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {
    private String userId;
    private String nickname;
    private String userName;
    private String userImg;
    private String email;
}