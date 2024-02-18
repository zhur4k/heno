package com.heno.service;

import com.heno.dto.SignUpDto;
import com.heno.model.User;
import com.heno.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This service class handles user authentication and registration operations.
 */
@Service
public class UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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
