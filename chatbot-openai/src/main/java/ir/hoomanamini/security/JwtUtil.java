package ir.hoomanamini.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret key for signing the JWT, generated securely
    private final Key secretKey;

    // Token validity in milliseconds (1 hour by default)
    @Value("${jwt.expiration:3600000}")
    private long jwtExpirationMs;

    public JwtUtil() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // Generate a token for a given username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Configurable expiration
                .signWith(secretKey)
                .compact();
    }

    // Extract the username (subject) from the JWT token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Validate the token for the given username and check expiration
    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    // Retrieve claims from the JWT token
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if the token has expired
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
