package com.heno.service;

import com.heno.dto.EmployeeAddDto;
import com.heno.dto.EmployeeEditDto;
import com.heno.dto.mapper.EmployeeAddDtoMapper;
import com.heno.dto.mapper.EmployeeEditDtoMapper;
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
    private final EmployeeAddDtoMapper employeeAddDtoMapper;
    private final EmployeeEditDtoMapper employeeEditDtoMapper;

    /**
     * Constructor for UserService.
     *
     * @param employeeAddDtoMapper      The SignUpDtoMapper for mapping SignUpDto to User entity.
     * @param employeeEditDtoMapper     The UserEditDtoMapper for mapping UserEditDto to User entity.
     * @param userRepository        The UserRepository for accessing user data.
     * @param passwordEncoder       The PasswordEncoder for encoding user passwords.
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmployeeAddDtoMapper employeeAddDtoMapper, EmployeeEditDtoMapper employeeEditDtoMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeAddDtoMapper = employeeAddDtoMapper;
        this.employeeEditDtoMapper = employeeEditDtoMapper;
    }

    /**
     * Adds a new user.
     *
     * @param employeeAddDto The SignUpDto containing information for creating a new user.
     * @throws RuntimeException if the username already exists.
     */
    public void addUser(EmployeeAddDto employeeAddDto) {
        if (userRepository.existsByUsername(employeeAddDto.username())) {
            throw new RuntimeException("Login is already taken");
        }
        User user = employeeAddDtoMapper.apply(employeeAddDto);
        userRepository.save(user);
    }

    /**
     * Edits an existing user.
     *
     * @param employeeEditDto The UserEditDto containing information for editing an existing user.
     */
    public void editUser(EmployeeEditDto employeeEditDto) {
        User user = employeeEditDtoMapper.apply(employeeEditDto);
        userRepository.save(user);
    }
}
