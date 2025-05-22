package com.pixel_game.pixel.Entity;
import com.pixel_game.pixel.DTO.Request.auth.JoinRequest;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String userId;
    private String password;
    private String email;
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User (JoinRequest req) {
        this.userId = req.getUserId();
        this.password = req.getPassword();
        this.email = req.getEmail();
        this.name = req.getName();
    }


}
