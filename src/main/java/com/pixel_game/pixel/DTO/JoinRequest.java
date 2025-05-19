package com.pixel_game.pixel.DTO;

import com.pixel_game.pixel.Entity.User;
import com.pixel_game.pixel.Entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {
    private String userId;
    private String password;
    private String passwordCheck;
    private String email;
    private String name;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .userId(userId)
                .password(password)
                .email(email)
                .name(name)
                .role(UserRole.USER)
                .build();
    }


}
