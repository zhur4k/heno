package com.heno.dto.mapper;

import com.heno.dto.SignUpDto;
import com.heno.dto.UserEditDto;
import com.heno.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserEditDtoMapper implements Function<UserEditDto, User> {
    @Override
    public User apply(UserEditDto userEditDto) {
        if (userEditDto == null) {
            throw new IllegalArgumentException("UserEditDto cannot be null");
        }
        User user  = new User();
        user.setId(userEditDto.id());
        user.setNumber(userEditDto.number());
        user.setFIO(userEditDto.FIO());
        user.setEmail(userEditDto.email());
        user.setRoles(userEditDto.roles());
        user.setUsername(userEditDto.username());
        user.setPassword(userEditDto.password());
        return user;
    }
}
