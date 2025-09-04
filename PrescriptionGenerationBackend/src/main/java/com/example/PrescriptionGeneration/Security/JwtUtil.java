package com.example.PrescriptionGeneration.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;
import java.util.Date;
import io.github.cdimascio.dotenv.Dotenv;

@Component
public class JwtUtil {

  // expiration time in milliseconds
  //  60 minute-> 1 hour
  //  60 seconds -> 1 minute
  //  1000 milliseconds -> 1 second
  //  so 10 hours in milliseconds= 1000 * 60 * 60 * 10

    private final String SECRET;
    private final long EXPIRATION_TIME;

    public JwtUtil() {
        Dotenv dotenv = Dotenv.load();
        this.SECRET = dotenv.get("JWT_SECRET");
        this.EXPIRATION_TIME = Long.parseLong(dotenv.get("JWT_EXPIRATION"));
    }

    // Generate token with email as subject
    // Signing algorithm is HMAC256
    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    // Validate token and return email
    // Verifies the JWT signature using the same secret string.
    // If valid, extracts and returns the email from the token.
    // If invalid, throws JWTVerificationException.

    public String validateTokenAndGetEmail(String token) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getSubject();
    }
}
