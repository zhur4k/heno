package com.heno.controller;

import com.heno.dto.EmployeeAddDto;
import com.heno.dto.EmployeeEditDto;
import com.heno.model.Role;
import com.heno.model.User;
import com.heno.repository.UserRepository;
import com.heno.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
     * Test for the {@link EmployeeController#addEmployee(EmployeeAddDto employeeAddDto)} method.
     * Verifies that the endpoint returns a success response after adding a new user.
     */
    @Test
    void testAddEmployeeEndpoint_Success() {
        // Arrange (no additional setup needed)

        // Act
        ResponseEntity<?> response = employeeController.addEmployee(
                new EmployeeAddDto("test@email.com"
                        , "John Doe"
                        , "123456789"
                        , "testUser"
                        , "password"
                        , Set.of(Role.SALESMAN)));

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService).addUser(Mockito.any(EmployeeAddDto.class));
    }

    /**
     * Test for the {@link EmployeeController#editEmployee(EmployeeEditDto employeeEditDto)} method.
     * Verifies that the endpoint returns a success response after editing an existing user.
     */
    @Test
    void testEditEmployeeEndpoint_Success() {
        // Arrange (no additional setup needed)

        // Act
        ResponseEntity<?> response = employeeController.editEmployee(new EmployeeEditDto(
                1L
                , "test@email.com"
                , "John Doe"
                , "123456789"
                , "testUser"
                , "password"
                , Set.of(Role.SALESMAN)));

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService).editUser(Mockito.any(EmployeeEditDto.class));
    }

    /**
     * Test for the success scenario of retrieving all employee roles.
     */
    @Test
    void testGetAllEmployeeRoles_Success() {
        // Replace with your expected roles
        Role[] expectedRoles = Role.values();
        // Test the endpoint
        ResponseEntity<?> response = employeeController.getAllEmployeeRoles();

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(expectedRoles, (Role[]) response.getBody());
    }

    /**
     * Test for the scenario where an exception occurs while retrieving employee roles.
     */
    @Test
    void testGetAllEmployeeRoles_Exception() {
        // Mock the Role class and its static method values()
        try (MockedStatic<Role> roleMock = mockStatic(Role.class)) {
            roleMock.when(Role::values).thenThrow(new RuntimeException("Some error message"));

            // Test the endpoint
            ResponseEntity<?> response = employeeController.getAllEmployeeRoles();

            // Assertions
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertEquals("Some error message", response.getBody());
        }
    }

}
