package com.heno.dto.mapper;

import com.heno.dto.EmployeeAddDto;
import com.heno.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmployeeAddDtoMapper implements Function<EmployeeAddDto, User> {
    private final PasswordEncoder passwordEncoder;

    public EmployeeAddDtoMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User apply(EmployeeAddDto employeeAddDto) {
        if (employeeAddDto == null) {
            throw new IllegalArgumentException("EmployeeAddDto cannot be null");
        }
        User user  = new User();
        user.setNumber(employeeAddDto.number());
        user.setFIO(employeeAddDto.FIO());
        user.setEmail(employeeAddDto.email());
        user.setRoles(employeeAddDto.roles());
        user.setUsername(employeeAddDto.username());
        user.setPassword(passwordEncoder.encode(employeeAddDto.password()));
        return user;
    }
}
