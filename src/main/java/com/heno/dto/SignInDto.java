package com.heno.dto;


/**
 * Dto record to sign in user information.
 */
public record SignInDto(
        String username,
        String password
) {
}
