package com.heno.service;

import com.heno.dto.SignUpDto;
import com.heno.dto.UserEditDto;
import com.heno.dto.mapper.SignUpDtoMapper;
import com.heno.dto.mapper.UserEditDtoMapper;
import com.heno.model.User;
import com.heno.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SignUpDtoMapper signUpDtoMapper;
    private final UserEditDtoMapper userEditDtoMapper;

    /**
     * Constructor for UserService.
     *
     * @param userRepository      The UserRepository for accessing user data.
     * @param passwordEncoder     The PasswordEncoder for encoding user passwords.
     * @param signUpDtoMapper     The SignUpDtoMapper for mapping SignUpDto to User entity.
     * @param userEditDtoMapper   The UserEditDtoMapper for mapping UserEditDto to User entity.
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       SignUpDtoMapper signUpDtoMapper, UserEditDtoMapper userEditDtoMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.signUpDtoMapper = signUpDtoMapper;
        this.userEditDtoMapper = userEditDtoMapper;
    }

    /**
     * Adds a new user.
     *
     * @param signUpDto The SignUpDto containing information for creating a new user.
     * @throws RuntimeException if the username already exists.
     */
    public void addUser(SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.username())) {
            throw new RuntimeException("Login is already taken");
        }
        User user = signUpDtoMapper.apply(signUpDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Edits an existing user.
     *
     * @param userEditDto The UserEditDto containing information for editing an existing user.
     */
    public void editUser(UserEditDto userEditDto) {
        User user = userEditDtoMapper.apply(userEditDto);
        userRepository.save(user);
    }
}
