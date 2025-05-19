package com.pixel_game.pixel.controller;
import com.pixel_game.pixel.DTO.JoinRequest;
import com.pixel_game.pixel.DTO.LoginRequest;
import com.pixel_game.pixel.Entity.User;
import com.pixel_game.pixel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinRequest req) {
        userService.join(req);
        return ResponseEntity.ok("회원사입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        String token = userService.login(req);
        return ResponseEntity.ok().body(Collections.singletonMap("token", token));
    }

}
