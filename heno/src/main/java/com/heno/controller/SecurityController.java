package com.heno.controller;

import com.heno.dto.SignInDto;
import com.heno.dto.SignUpDto;
import com.heno.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents a REST controller for handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/auth")
public class SecurityController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles the authentication process for user sign-in.
     *
     * @param signInDto The DTO containing the username and password for sign-in.
     * @return ResponseEntity containing a JWT token on successful authentication or an UNAUTHORIZED status on failure.
     */
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
        try {
            return ResponseEntity.ok(userService.loginUser(signInDto));
        }catch(BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Handles the registration process for user sign-up.
     *
     * @param signUpDto The DTO containing the User info for sign-up.
     * @return ResponseEntity with a success message on successful sign-up or a BAD_REQUEST status if the username already exists.
     */
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        try {
            userService.addUser(signUpDto);
            return ResponseEntity.ok("Success signUp!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose a different name");
        }
    }
}
