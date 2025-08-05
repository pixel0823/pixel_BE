package project.game.pixel.entity;

import jakarta.persistence.*;
import lombok.*;
import project.game.pixel.dto.request.game.GameRoomInitRequestDto;

@Entity
@Table(name="MULTI_ROOM")
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long roomId;

    @Column(name = "OWNER_ID", nullable = false)
    private String ownerId;

    @Column(name = "ROOM_NAME", nullable = false)
    private String roomName;

    @Column(name = "OWNER_IP", nullable = false)
    private String ownerIp;

    @Column(name = "ROOM_PASSWORD")
    private String roomPassword;

    public GameRoom(GameRoomInitRequestDto dto) {
        this.ownerId = dto.getOwnerId();
        this.roomName = dto.getRoomName();
        this.ownerIp = dto.getOwnerIp();
        this.roomPassword = dto.getPassword();
    }
}
