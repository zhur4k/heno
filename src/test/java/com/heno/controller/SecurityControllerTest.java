package com.heno.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heno.dto.SignUpDto;
import com.heno.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Unit tests for the SecurityController class.
 */
@ExtendWith(MockitoExtension.class)
class SecurityControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    private SecurityController securityController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        // Initialize the MockMvc instance for testing
        mockMvc = standaloneSetup(securityController).build();
    }

    /**
     * Tests a successful sign-up process.
     *
     * @throws Exception If an error occurs during the test.
     */
    @Test
    void signUp_Success() throws Exception {
        // Create a Sign-Up DTO with new user credentials
        SignUpDto signUpDto = new SignUpDto("email","fio","number","newUser", "password", Collections.emptySet());


        // Mock the behavior of the UserRepository to indicate that the username does not exist
        doNothing().when(userService).addUser(signUpDto);

        // Perform the HTTP request to the sign-up endpoint and verify the expected success status
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(signUpDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Success signUp!"));
    }

    /**
     * Tests an unsuccessful sign-up due to an existing user.
     *
     * @throws Exception If an error occurs during the test.
     */
    @Test
    void signUp_UserAlreadyExists() throws Exception {
        // Create a Sign-Up DTO with credentials of an existing user
        SignUpDto signUpDto = new SignUpDto("email","fio","number","existingUser", "password", Collections.emptySet());

        // Mock the behavior of the UserRepository to indicate that the username already exists
        doThrow(new RuntimeException("Choose a different name")).when(userService).addUser(signUpDto);

        // Perform the HTTP request to the sign-up endpoint and verify the expected bad request status
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(signUpDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Choose a different name"));
    }
}
