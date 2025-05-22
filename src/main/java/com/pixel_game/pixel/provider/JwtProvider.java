package com.pixel_game.pixel.provider;

import com.pixel_game.pixel.Entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key secretKey;
    private final long expiration = 1000L * 60 * 60 * 24;

    public JwtProvider() {
        String rawSecret = System.getenv("JWT_SECRET");
        if (rawSecret == null || rawSecret.isBlank()) {
            throw  new IllegalStateException("JWT_SECRET 환경 변수가 설정되어 있지 않습니다.");
        }
        this.secretKey = Keys.hmacShaKeyFor(rawSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String userId, UserRole role) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserId(String token) {
        return getClaims(token).getSubject();
    }

    public UserRole getRole(String token) {
        String roleStr = getClaims(token).get("role", String.class);
        return UserRole.valueOf(roleStr);
    }

    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
