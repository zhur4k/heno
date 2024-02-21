package com.heno.dto.mapper;

import com.heno.dto.EmployeeEditDto;
import com.heno.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting EmployeeEditDto to User entity.
 */
@Service
public class EmployeeEditDtoMapper implements Function<EmployeeEditDto, User> {

    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs an EmployeeEditDtoMapper with the specified PasswordEncoder dependency.
     *
     * @param passwordEncoder The password encoder for encoding user passwords.
     */
    public EmployeeEditDtoMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Converts an EmployeeEditDto object to a User entity.
     *
     * @param employeeEditDto The EmployeeEditDto object to convert.
     * @return A User entity converted from the EmployeeEditDto.
     * @throws IllegalArgumentException if employeeEditDto is null.
     */
    @Override
    public User apply(EmployeeEditDto employeeEditDto) {
        if (employeeEditDto == null) {
            throw new IllegalArgumentException("EmployeeEditDto cannot be null");
        }
        User user = new User();
        user.setId(employeeEditDto.id());
        user.setNumber(employeeEditDto.number());
        user.setFIO(employeeEditDto.FIO());
        user.setEmail(employeeEditDto.email());
        user.setRoles(employeeEditDto.roles());
        user.setUsername(employeeEditDto.username());
        user.setPassword(passwordEncoder.encode(employeeEditDto.password()));
        return user;
    }
}
