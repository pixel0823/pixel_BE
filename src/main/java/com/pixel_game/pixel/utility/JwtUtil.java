package com.pixel_game.pixel.utility;

import com.pixel_game.pixel.Entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secretKey;
    private final long expiration = 1000L * 60 * 60 * 24;

    public JwtUtil() {
        String rawSecret = System.getenv("JWT_SECRET");
        if (rawSecret == null) {
            throw new IllegalStateException("JWT_SECRET 환경 변수가 설저오디어 있지 않습니다.");
        }
        this.secretKey = Base64.getEncoder().encodeToString(rawSecret.getBytes());
    }

    public String createToken(String userId, UserRole role) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role", role.name());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserId(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public UserRole getRole(String token) {
        Claims claims = getClaims(token);
        String role = claims.get("role", String.class);
        return UserRole.valueOf(role);
    }
}
