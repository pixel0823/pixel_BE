package project.game.pixel.dto.request.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameRoomInitRequestDto {
    private String ownerId;

    private String roomName;

    private String ownerIp;

    private String password;
}
