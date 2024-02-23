package com.example.bank.service;

import com.example.bank.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthService {
    private static final String SECRET_KEY = "hdejqdqlcqsb;cq:sc"; // Change this to a strong secret key
    private static final long EXPIRATION_TIME_MS = 3600000; // 1 hour expiration time

    public static String generateToken(User user) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(key, SignatureAlgorithm.HS256) // Sign the token with the provided key
                .compact();
    }

    public static boolean validateToken(String token, User user) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            // Check if the token's subject matches the user's username
            String subject = claimsJws.getBody().getSubject();
            if (!user.getUsername().equals(subject)) {
                return false;
            }

            // Check if the token is expired
            Date expiration = claimsJws.getBody().getExpiration();
            if (expiration != null && expiration.before(new Date())) {
                return false;
            }

            // Additional validation checks can be added here

        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public static boolean verifySignature(Jws<Claims> claimsJws, Key key) {
        try {
            // Serialize the claims to JSON
            String serializedClaims = Jwts.builder().setClaims(claimsJws.getBody()).compact();
            // Verify the token's signature using the provided key
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(serializedClaims).equals(claimsJws);
        } catch (Exception e) {
            return false;
        }
    }

}
