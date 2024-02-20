package com.heno.dto.mapper;

import com.heno.dto.SignUpDto;
import com.heno.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SignUpDtoMapper implements Function<SignUpDto, User> {
    @Override
    public User apply(SignUpDto signUpDto) {
        User user  = new User();
        user.setNumber(signUpDto.number());
        user.setFIO(signUpDto.FIO());
        user.setEmail(signUpDto.email());
        user.setRoles(signUpDto.roles());
        user.setUsername(signUpDto.username());
        user.setPassword(signUpDto.password());
        return user;
    }
}
