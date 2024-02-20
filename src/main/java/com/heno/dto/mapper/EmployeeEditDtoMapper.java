package com.heno.dto.mapper;

import com.heno.dto.EmployeeEditDto;
import com.heno.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmployeeEditDtoMapper implements Function<EmployeeEditDto, User> {
    private final PasswordEncoder passwordEncoder;

    public EmployeeEditDtoMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User apply(EmployeeEditDto employeeEditDto) {
        if (employeeEditDto == null) {
            throw new IllegalArgumentException("EmployeeEditDto cannot be null");
        }
        User user  = new User();
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
