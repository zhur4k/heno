package com.heno.dto;

import com.heno.model.Role;

import java.util.Set;

/**
 * Dto class to edit user information.
 */
public record EmployeeEditDto(
        Long id,
        String email,
        String FIO,
        String number,
        String username,
        String password,
        Set<Role> roles
)
{
}