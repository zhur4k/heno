package com.heno.controller;

import com.heno.config.JwtCore;
import com.heno.dto.SignInDto;
import com.heno.dto.SignUpDto;
import com.heno.model.User;
import com.heno.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents a REST controller for handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/auth")
public class SecurityController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtCore jwtCore;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtCore(JwtCore jwtCore) {
        this.jwtCore = jwtCore;
    }

    /**
     * Handles the authentication process for user sign-in.
     *
     * @param signInDto The DTO containing the username and password for sign-in.
     * @return ResponseEntity containing a JWT token on successful authentication or an UNAUTHORIZED status on failure.
     */
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInDto.username(),
                            signInDto.password()
                    ));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }

    /**
     * Handles the registration process for user sign-up.
     *
     * @param signUpDto The DTO containing the username and password for sign-up.
     * @return ResponseEntity with a success message on successful sign-up or a BAD_REQUEST status if the username already exists.
     */
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.username())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose a different name");
        }
        User user = new User();
        user.setUsername(signUpDto.username());
        user.setPassword(passwordEncoder.encode(signUpDto.password()));
        userRepository.save(user);
        return ResponseEntity.ok("Success signUp!");
    }
}
