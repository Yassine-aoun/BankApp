package test;

import com.example.bank.models.User;
import com.example.bank.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {
    private static final String SECRET_KEY = "hdejqdqlcqsb;cq:sc";

    @Test
    void validateToken_validToken_returnsFalse() {
        // Given
        User user = new User("testUser","TestUser");
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();

        // When
        boolean result = AuthService.validateToken(token, user);

        // Then
        assertFalse(result);
    }

    @Test
    void validateToken_invalidToken_returnsFalse() {
        // Given
        String invalidToken = "invalidToken";
        User user = new User("testUser","TestUser");

        // When
        boolean result = AuthService.validateToken(invalidToken, user);

        // Then
        assertFalse(result);
    }

    @Test
    void verifySignature_validSignature_returnsFalse() {
        // Given
        User user = new User("testUser", "TestUser");
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

        // When
        boolean result = AuthService.verifySignature(claimsJws, key);

        // Then
        assertFalse(result);
    }
}
