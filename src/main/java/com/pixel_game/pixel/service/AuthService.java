package com.pixel_game.pixel.service;

import com.pixel_game.pixel.DTO.Request.auth.IdCheckRequest;
import com.pixel_game.pixel.DTO.Request.auth.JoinRequest;
import com.pixel_game.pixel.DTO.Request.auth.LoginRequest;
import com.pixel_game.pixel.DTO.Response.auth.IdCheckResponseDTO;
import com.pixel_game.pixel.DTO.Response.auth.JoinResponseDTO;
import com.pixel_game.pixel.DTO.Response.auth.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<? super IdCheckResponseDTO> idCheck(IdCheckRequest dto);
    ResponseEntity<? super LoginResponseDTO> Login(LoginRequest dto);
    ResponseEntity<? super JoinResponseDTO> Join(JoinRequest dto);
}
