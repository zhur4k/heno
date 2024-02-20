package com.heno.controller;

import com.heno.dto.SignUpDto;
import com.heno.dto.UserEditDto;
import com.heno.model.Role;
import com.heno.model.User;
import com.heno.repository.UserRepository;
import com.heno.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link EmployeeController} class.
 */
public class EmployeeControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    /**
     * Test for the {@link EmployeeController#allEmployees()} method.
     * Verifies that the endpoint returns the expected string.
     */
    @Test
    void testAllEmployeesEndpoint() {
        // Arrange (no additional setup needed)

        // Act
        String result = employeeController.allEmployees();

        // Assert
        assertEquals("allEmployees", result);
    }

    /**
     * Test for the {@link EmployeeController#getAllEmployees()} method.
     * Verifies that the endpoint returns a success response with the list of users.
     */
    @Test
    void testGetAllEmployeesEndpoint_Success() {
        // Arrange
        List<User> mockUsers = Collections.singletonList(new User());
        when(userRepository.findAll()).thenReturn(mockUsers);

        // Act
        ResponseEntity<?> response = employeeController.getAllEmployees();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());
        verify(userRepository).findAll();
    }

    /**
     * Test for the {@link EmployeeController#getAllEmployees()} method.
     * Verifies that the endpoint returns an error response when an exception occurs.
     */
    @Test
    void testGetAllEmployeesEndpoint_Exception() {
        // Arrange
        when(userRepository.findAll()).thenThrow(new RuntimeException("Test exception"));

        // Act
        ResponseEntity<?> response = employeeController.getAllEmployees();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Test exception", response.getBody());
        verify(userRepository).findAll();
    }

    /**
     * Test for the {@link EmployeeController#addEmployee(SignUpDto signUpDto)} method.
     * Verifies that the endpoint returns a success response after adding a new user.
     */
    @Test
    void testAddEmployeeEndpoint_Success() {
        // Arrange (no additional setup needed)

        // Act
        ResponseEntity<?> response = employeeController.addEmployee(
                new SignUpDto("test@email.com"
                        , "John Doe"
                        , "123456789"
                        , "testUser"
                        , "password"
                        , Set.of(Role.SALESMAN)));

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService).addUser(Mockito.any(SignUpDto.class));
    }

    /**
     * Test for the {@link EmployeeController#editEmployee(UserEditDto userEditDto)} method.
     * Verifies that the endpoint returns a success response after editing an existing user.
     */
    @Test
    void testEditEmployeeEndpoint_Success() {
        // Arrange (no additional setup needed)

        // Act
        ResponseEntity<?> response = employeeController.editEmployee(new UserEditDto(
                1L
                , "test@email.com"
                , "John Doe"
                , "123456789"
                , "testUser"
                , "password"
                , Set.of(Role.SALESMAN)));

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService).editUser(Mockito.any(UserEditDto.class));
    }
}
