package com.heno.service;

import com.heno.config.JwtCore;
import com.heno.dto.SignInDto;
import com.heno.dto.SignUpDto;
import com.heno.model.User;
import com.heno.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtCore jwtCore;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    void loginUser_Success() {
        SignInDto signInDto = new SignInDto("testUser", "testPassword");
        Authentication mockAuthentication = mock(Authentication.class);

        // Mocking authentication response
        when(authenticationManager.authenticate(any())).thenReturn(mockAuthentication);
        when(jwtCore.generateToken(mockAuthentication)).thenReturn("dummyToken");

        String token = userService.loginUser(signInDto);

        assertNotNull(token);
        assertEquals("dummyToken", token);
    }

    @Test
    void addUser_Success() {
        SignUpDto signUpDto = new SignUpDto("email", "FIO", "number", "newUser", "password", Collections.emptySet());
        User mockUser = new User();

        // Mocking repository response
        when(userRepository.existsByUsername(signUpDto.username())).thenReturn(false);
        when(passwordEncoder.encode(signUpDto.password())).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenReturn(mockUser);

        userService.addUser(signUpDto);

        // Verifying that save method is called with the correct user object
        verify(userRepository, times(1)).save(argThat(argument ->
                argument.getEmail().equals(signUpDto.email()) &&
                        argument.getFIO().equals(signUpDto.FIO()) &&
                        argument.getNumber().equals(signUpDto.number()) &&
                        argument.getUsername().equals(signUpDto.username()) &&
                        argument.getPassword().equals("encodedPassword") &&
                        argument.getRoles().equals(signUpDto.roles())
        ));
    }

    @Test
    void addUser_UserAlreadyExists() {
        SignUpDto signUpDto = new SignUpDto("email", "FIO", "number", "existingUser", "password",Collections.emptySet());

        // Mocking repository response
        when(userRepository.existsByUsername(signUpDto.username())).thenReturn(true);

        // Verifying that addUser throws RuntimeException with the expected message
        assertThrows(RuntimeException.class, () -> userService.addUser(signUpDto), "Login is exist");
    }



}