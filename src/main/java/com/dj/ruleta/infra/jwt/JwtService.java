package com.dj.ruleta.infra.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dj.ruleta.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {
    private static final String ISSUER = "auth0";
    @Value("${api.security.jwt.secret}")
    private String apiSecureKey;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecureKey);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withClaim("username", user.getUsername())
                    .withClaim("fullName", user.getFullName())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            return new ErrorTokenResponseDto("Cannot create token").toString();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Token is required");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecureKey);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            System.out.println("Error");
            return null;
        }
    }

    private Instant getExpiration() {
        return LocalDateTime.now().plusMinutes(60).toInstant(ZoneOffset.of("-05:00"));
    }
}
