package com.mohitmarfatia.moskitchen.helper;


import com.mohitmarfatia.moskitchen.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTHelper {
    private String SECRET_KEY = "cr666N7wIV+KJ2xOQpWtcfAekL4YXd9gbnJMs8SJ9sI=";

    // Extract username from the token
    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public UserRole extractUserRole(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.get("role", UserRole.class);
    }

    // Extract expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Check if token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Generate token
    public String generateToken(Long userId, UserRole role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("iat", new Date(System.currentTimeMillis()));
        claims.put("exp", new Date(System.currentTimeMillis() + 1000 * 60));  // Token valid for 10 hours
        claims.put("role", role);
        claims.put("sub", userId.toString());
        return createToken(claims);
    }

    // Create token with claims
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    // Validate token
    public Boolean validateToken(String token) {
//        final String extractedUsername = extractUserId(token);
//        return (extractedUsername.equals(username) && !isTokenExpired(token));
        return !isTokenExpired(token);
    }
}

