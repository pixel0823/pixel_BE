package project.game.pixel.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String userId;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,13}$")
    private String password;

    private String userName;

    @Email
    private String email;

    private String nickname;

    private String profileImageUrl;
}
