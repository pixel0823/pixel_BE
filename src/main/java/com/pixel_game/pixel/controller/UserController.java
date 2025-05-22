package com.pixel_game.pixel.controller;
import com.pixel_game.pixel.DTO.Request.auth.IdCheckRequest;
import com.pixel_game.pixel.DTO.Request.auth.JoinRequest;
import com.pixel_game.pixel.DTO.Request.auth.LoginRequest;
import com.pixel_game.pixel.DTO.Response.auth.IdCheckResponseDTO;
import com.pixel_game.pixel.DTO.Response.auth.JoinResponseDTO;
import com.pixel_game.pixel.DTO.Response.auth.LoginResponseDTO;
import com.pixel_game.pixel.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDTO> idCheck (
            @RequestBody @Valid IdCheckRequest requestBody
    ) {
        ResponseEntity<? super IdCheckResponseDTO> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("/join")
    public ResponseEntity<? super JoinResponseDTO> join(
            @RequestBody @Valid JoinRequest requestBody
    ) {
        ResponseEntity<? super JoinResponseDTO> response = authService.Join(requestBody);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<? super LoginResponseDTO> login(
            @RequestBody @Valid LoginRequest requestBody
    )  {
        ResponseEntity<? super LoginResponseDTO> response = authService.Login(requestBody);
        return response;
    }

}
