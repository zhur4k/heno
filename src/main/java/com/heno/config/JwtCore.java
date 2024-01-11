package com.heno.config;

import com.heno.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import java.util.Date;

/**
 * Component for handling JSON Web Tokens (JWT) in the context of Spring Security authentication.
 */
@Component
public class JwtCore {

    // Value of 'secret' and 'lifetime' obtained from the configuration file (e.g., application.yml)
    @Value("${testing.app.secret}")
    private String secret;

    @Value("${testing.app.lifetime}")
    private int lifetime;

    /**
     * Generates a JWT token based on user authentication.
     *
     * @param authentication User authentication.
     * @return JWT token.
     */
    public String generateToken(Authentication authentication) {
        // Retrieve the user from authentication
        User user = (User) authentication.getPrincipal();

        // Build JWT token with specified signature, issuance time, and expiration time
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + lifetime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * Retrieves the username from a JWT token.
     *
     * @param token JWT token.
     * @return Username extracted from the token.
     */
    public String getNameFromJwt(String token) {
        // Decrypt the token and extract data from it
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
