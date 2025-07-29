package project.game.pixel.dto;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String nickname;
    private String userName;
    private String userImg;
    private String email;
}