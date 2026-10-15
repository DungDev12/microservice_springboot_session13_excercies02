package com.exam.identityservice.security.jwt;

import com.exam.identityservice.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int expired;

    public String generateToken(User user) {
        Date dateExpiration = new Date(new Date().getTime() + expired);
        SecretKey key= Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
        return Jwts.builder()
                .id(user.getUuid().toString())
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(dateExpiration)
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }
}
