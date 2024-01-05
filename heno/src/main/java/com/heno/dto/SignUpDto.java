package com.heno.dto;

import com.heno.model.Role;

import java.util.Set;

/**
 * Dto class to sign up user information.
 */
public record SignUpDto(
        String email,
        String FIO,
        String number,
        String username,
        String password,
        Set<Role> roles
)
{
}