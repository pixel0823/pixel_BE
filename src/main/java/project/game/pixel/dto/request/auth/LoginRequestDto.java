package project.game.pixel.dto.request.auth;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String userId;
    private String password;
}

