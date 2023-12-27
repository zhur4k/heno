package com.heno.dto;

/**
 * Dto class to sign up user information.
 */
public record SignUpDto(
        String username,
        String password
)
{
}