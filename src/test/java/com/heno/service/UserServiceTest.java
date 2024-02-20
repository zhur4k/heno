package com.heno.service;

import com.heno.dto.EmployeeAddDto;
import com.heno.dto.EmployeeEditDto;
import com.heno.dto.mapper.EmployeeAddDtoMapper;
import com.heno.dto.mapper.EmployeeEditDtoMapper;
import com.heno.model.User;
import com.heno.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UserService class.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmployeeAddDtoMapper employeeAddDtoMapper;

    @Mock
    private EmployeeEditDtoMapper employeeEditDtoMapper;

    @InjectMocks
    private UserService userService;

    /**
     * Tests the addUser method for a successful case where the user is added without any issues.
     */
    @Test
    void addUser_Success() {
        // Given
        EmployeeAddDto employeeAddDto = new EmployeeAddDto("email", "FIO", "number", "newUser", "password", Collections.emptySet());
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail(employeeAddDto.email());
        mockUser.setFIO(employeeAddDto.FIO());
        mockUser.setNumber(employeeAddDto.number());
        mockUser.setUsername(employeeAddDto.username());
        mockUser.setPassword(employeeAddDto.password());
        mockUser.setRoles(employeeAddDto.roles());

        // Mocking repository response
        when(userRepository.existsByUsername(employeeAddDto.username())).thenReturn(false);
        when(employeeAddDtoMapper.apply(employeeAddDto)).thenReturn(mockUser);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        // When
        userService.addUser(employeeAddDto);

        // Then
        // Verifying that save method is called with the correct user object
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        // Проверки с использованием assertEquals
        assertEquals(mockUser.getId(), capturedUser.getId());
        assertEquals(employeeAddDto.email(), capturedUser.getEmail());
        assertEquals(employeeAddDto.FIO(), capturedUser.getFIO());
        assertEquals(employeeAddDto.number(), capturedUser.getNumber());
        assertEquals(employeeAddDto.username(), capturedUser.getUsername());
        assertEquals(employeeAddDto.password(), capturedUser.getPassword());
        assertEquals(employeeAddDto.roles(), capturedUser.getRoles());
    }


    /**
     * Tests the addUser method when the username already exists, expecting a RuntimeException.
     */
    @Test
    void addUser_UserAlreadyExists() {
        // Given
        EmployeeAddDto employeeAddDto = new EmployeeAddDto("email", "FIO", "number", "existingUser", "password", Collections.emptySet());

        // Mocking repository response
        when(userRepository.existsByUsername(employeeAddDto.username())).thenReturn(true);

        // Then
        // Verifying that addUser throws RuntimeException with the expected message
        assertThrows(RuntimeException.class, () -> userService.addUser(employeeAddDto), "Login is already taken");
    }

    /**
     * Tests the editUser method for an existing user.
     */
    @Test
    void editUser_Success() {
        // Given
        EmployeeEditDto employeeEditDto = new EmployeeEditDto(2L, "email", "FIO", "number", "editedUser", "password", Collections.emptySet());
        User mockUser = new User();
        mockUser.setId(employeeEditDto.id());
        mockUser.setEmail(employeeEditDto.email());
        mockUser.setFIO(employeeEditDto.FIO());
        mockUser.setNumber(employeeEditDto.number());
        mockUser.setUsername(employeeEditDto.username());
        mockUser.setPassword(employeeEditDto.password());
        mockUser.setRoles(employeeEditDto.roles());
        // Mocking repository response
        when(employeeEditDtoMapper.apply(employeeEditDto)).thenReturn(mockUser);
        when(userRepository.save(any())).thenReturn(mockUser);  // Use any() here

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        // When
        userService.editUser(employeeEditDto);

        // Then
        // Verifying that save method is called with the correct user object
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        // Проверки с использованием assertEquals
        assertEquals(employeeEditDto.id(), capturedUser.getId());
        assertEquals(employeeEditDto.email(), capturedUser.getEmail());
        assertEquals(employeeEditDto.FIO(), capturedUser.getFIO());
        assertEquals(employeeEditDto.number(), capturedUser.getNumber());
        assertEquals(employeeEditDto.username(), capturedUser.getUsername());
        assertEquals(employeeEditDto.password(), capturedUser.getPassword());
        assertEquals(employeeEditDto.roles(), capturedUser.getRoles());
    }

}
