package com.quiz_platform.authenticationdb.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtUtil {
    Dotenv dotenv = Dotenv.load();

    private final Algorithm algorithm;
    private final int EXPIRATION_TIME = 2; // Expiration time in hours

    public JwtUtil() {
        final String secret = dotenv.get("JWT_SECRET");

        if (secret == null) {
            throw new IllegalArgumentException("No secret exists!");
        }

        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(LocalDateTime.now().plusHours(EXPIRATION_TIME)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .sign(algorithm);
    }

    /**
     * Verifies a given JWT token by decoding it and checking its validity.
     * Throws a {@link JWTVerificationException} if the token is invalid.
     *
     * @param token the JWT token to be verified
     * @return the decoded {@link DecodedJWT} if verification succeeds
     * @throws JWTVerificationException if the token verification fails
     */
    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
