package com.heno.config;

import com.heno.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.lang.reflect.Field;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Tests for the JwtCore class.
 */
@ExtendWith(MockitoExtension.class)
class JwtCoreTest {

    @Mock
    private Authentication authentication;

    @InjectMocks
    private JwtCore jwtCore;

    private String testSecret = "testSecret";
    private int testLifetime = 3600; // in seconds

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        jwtCore = new JwtCore();

        // Use reflection to set private fields
        Field secretField = JwtCore.class.getDeclaredField("secret");
        secretField.setAccessible(true);
        secretField.set(jwtCore, testSecret);

        Field lifetimeField = JwtCore.class.getDeclaredField("lifetime");
        lifetimeField.setAccessible(true);
        lifetimeField.set(jwtCore, testLifetime); // in seconds
    }


    /**
     * Tests the generation of a valid JWT token for a given authentication.
     */
    @Test
    void generateToken_ValidAuthentication_Success() {
        // Mock user authentication
        User user = new User();
        user.setUsername("testUser");
        when(authentication.getPrincipal()).thenReturn(user);

        // Generate token
        String token = jwtCore.generateToken(authentication);

        // Verify token is not null or empty
        assertNotNull(token);
        assertNotEquals("", token);

        // Verify token contains the expected claims
        Claims claims = Jwts.parser().setSigningKey(testSecret).parseClaimsJws(token).getBody();
        assertEquals("testUser", claims.getSubject());
    }

    /**
     * Tests the extraction of the username from a valid JWT token.
     */
    @Test
    void getNameFromJwt_ValidToken_Success() {
        // Create a valid token
        String token = Jwts.builder()
                .setSubject("testUser")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + testLifetime * 1000))
                .signWith(SignatureAlgorithm.HS256, testSecret)
                .compact();

        // Retrieve the username from the token
        String username = jwtCore.getNameFromJwt(token);

        // Verify the extracted username
        assertEquals("testUser", username);
    }

    /**
     * Tests the behavior when attempting to extract the username from an expired JWT token.
     */
    @Test
    void getNameFromJwt_ExpiredToken_Exception() {
        // Create an expired token
        String expiredToken = Jwts.builder()
                .setSubject("testUser")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() - 1000))  // Expired
                .signWith(SignatureAlgorithm.HS256, testSecret)
                .compact();

        // Verify that attempting to extract the username from an expired token throws an exception
        assertThrows(ExpiredJwtException.class, () -> jwtCore.getNameFromJwt(expiredToken));
    }
}
