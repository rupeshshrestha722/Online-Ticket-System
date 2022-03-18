package com.interview.project.onlineticketsystem_backend.securities.jwt;
import java.io.IOException;
import java.util.Date;

import com.interview.project.onlineticketsystem_backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This utility class contains helper methods used to generate and decode JWT
 * tokens.
 */
@Component
public class TokenUtils {

    /**
     * The claim key.
     */
    private static final String CLAIM_KEY = "sub";

    /**
     * The secret to sign the tokens.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * The token expiration in seconds.
     */
    @Value("${jwt.expiration-in-seconds}")
    private long expirationInSeconds;

    /**
     * The object mapper.
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Creates JWT token for the specified token payload.
     *
     * @param user
     *          the token payload
     * @return the JWT token
     */
    public String createJwtToken(User user) {
        final Date expiration = new Date(System.currentTimeMillis() + expirationInSeconds * 1000);

        try {
            return Jwts.builder().claim(CLAIM_KEY, objectMapper.writeValueAsString(user)).setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Cannot create JWT token", ex);
        }
    }

    /**
     * Decodes the JWT token.
     *
     * @param jwtToken
     *          the JWT token
     * @return the user info
     * @throws BadCredentialsException
     *           if the JWT token is invalid
     */
    public User decodeJwtToken(String jwtToken) {
        Claims claims;

        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
        } catch (Exception ex) {
            throw new BadCredentialsException(ex.getMessage(), ex);
        }

        String payload = (String) claims.get(CLAIM_KEY);
        try {
            return objectMapper.readValue(payload, User.class);
        } catch (IOException ex) {
            throw new BadCredentialsException("Invalid JWT token", ex);
        }
    }
}
