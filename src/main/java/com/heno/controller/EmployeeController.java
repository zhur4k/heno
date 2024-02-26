package com.heno.controller;

import com.heno.dto.EmployeeAddDto;
import com.heno.dto.EmployeeEditDto;
import com.heno.model.Role;
import com.heno.repository.UserRepository;
import com.heno.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for managing employees.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final UserRepository userRepository;
    private final UserService userService;

    /**
     * Constructor for EmployeeController.
     *
     * @param userRepository The UserRepository for accessing user data.
     * @param userService    The UserService for handling user-related operations.
     */
    public EmployeeController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * Endpoint to get all employees.
     *
     * @return v view all employees.
     */
    @GetMapping("/")
    String allEmployees() {
        return "allEmployees";
    }

    /**
     * Endpoint to retrieve all employees from the repository.
     *
     * @return ResponseEntity with the list of employees if successful, INTERNAL_SERVER_ERROR if an exception occurs.
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEmployees() {
        try {
            return ResponseEntity.ok(userRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint to add a new employee.
     *
     * @param employeeAddDto The SignUpDto containing information for creating a new user.
     * @return ResponseEntity indicating success or failure with appropriate HTTP status codes.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(EmployeeAddDto employeeAddDto) {
        try {
            userService.addUser(employeeAddDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint to edit an existing employee.
     *
     * @param employeeEditDto The UserEditDto containing information for editing an existing user.
     * @return ResponseEntity indicating success or failure with appropriate HTTP status codes.
     */
    @PostMapping("/edit")
    public ResponseEntity<?> editEmployee(EmployeeEditDto employeeEditDto) {
        try {
            userService.editUser(employeeEditDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    /**
     * Endpoint to retrieve all roles.
     *
     * @return ResponseEntity with the list of Roles if successful, INTERNAL_SERVER_ERROR if an exception occurs.
     */
    @GetMapping("/getAllRoles")
    public ResponseEntity<?> getAllEmployeeRoles() {
        try {
            return ResponseEntity.ok(Role.values());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
