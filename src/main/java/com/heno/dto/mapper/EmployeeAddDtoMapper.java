package com.heno.dto.mapper;

import com.heno.dto.EmployeeAddDto;
import com.heno.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting EmployeeAddDto to User entity.
 */
@Service
public class EmployeeAddDtoMapper implements Function<EmployeeAddDto, User> {

    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs an EmployeeAddDtoMapper with the specified PasswordEncoder dependency.
     *
     * @param passwordEncoder The password encoder for encoding user passwords.
     */
    public EmployeeAddDtoMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Converts an EmployeeAddDto object to a User entity.
     *
     * @param employeeAddDto The EmployeeAddDto object to convert.
     * @return A User entity converted from the EmployeeAddDto.
     * @throws IllegalArgumentException if employeeAddDto is null.
     */
    @Override
    public User apply(EmployeeAddDto employeeAddDto) {
        if (employeeAddDto == null) {
            throw new IllegalArgumentException("EmployeeAddDto cannot be null");
        }
        User user = new User();
        user.setNumber(employeeAddDto.number());
        user.setFIO(employeeAddDto.FIO());
        user.setEmail(employeeAddDto.email());
        user.setRoles(employeeAddDto.roles());
        user.setUsername(employeeAddDto.username());
        user.setPassword(passwordEncoder.encode(employeeAddDto.password()));
        return user;
    }
}
