package com.heno.service;

import com.heno.model.User;
import com.heno.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the UserUserDetailsService class.
 */
@ExtendWith(MockitoExtension.class)
class UserUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserUserDetailsService userUserDetailsService;
    @Test
    void loadUserByUsername_UserExists() {
        String username = "testUser";
        User mockUser = new User();
        mockUser.setUsername(username);

        // Mocking repository response
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        UserDetails userDetails = userUserDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }
    @Test
    void loadUserByUsername_UserNotFound() {
        String username = "nonExistingUser";

        // Mocking repository response
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userUserDetailsService.loadUserByUsername(username));
    }

}