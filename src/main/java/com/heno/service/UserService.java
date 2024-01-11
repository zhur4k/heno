package com.heno.service;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This service class handles user authentication and registration operations.
 */
@Service
public class UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtCore jwtCore;
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Autowired
    public void setJwtCore(JwtCore jwtCore) {
        this.jwtCore = jwtCore;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Authenticates a user based on the provided credentials and generates a JSON Web Token (JWT) for successful authentication.
     *
     * @param signInDto The sign-in data containing the username and password for authentication.
     * @return A JWT token representing the authenticated user.
     * @throws BadCredentialsException If the provided credentials are invalid.
     */
    public String loginUser(SignInDto signInDto){
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDto.username(),
                        signInDto.password()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtCore.generateToken(authentication);
    }
    /**
     * Registers a new user with the provided sign-up data.
     *
     * @param signUpDto The sign-up data containing the user info for registration.
     * @throws RuntimeException If a user with the specified username already exists.
     */
    public void addUser(SignUpDto signUpDto){
        if (userRepository.existsByUsername(signUpDto.username())) {
            throw new RuntimeException("Login is exist");
        }
        User user = new User();
        user.setEmail(signUpDto.email());
        user.setFIO(signUpDto.FIO());
        user.setNumber(signUpDto.number());
        user.setUsername(signUpDto.username());
        user.setPassword(passwordEncoder.encode(signUpDto.password()));
        user.setRoles(signUpDto.roles());
        userRepository.save(user);
    }

}
