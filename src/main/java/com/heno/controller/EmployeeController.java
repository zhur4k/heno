package com.heno.controller;

import com.heno.dto.SignUpDto;
import com.heno.dto.UserEditDto;
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
     * @return A string indicating all employees.
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
     * @param signUpDto The SignUpDto containing information for creating a new user.
     * @return ResponseEntity indicating success or failure with appropriate HTTP status codes.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(SignUpDto signUpDto) {
        try {
            userService.addUser(signUpDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint to edit an existing employee.
     *
     * @param userEditDto The UserEditDto containing information for editing an existing user.
     * @return ResponseEntity indicating success or failure with appropriate HTTP status codes.
     */
    @PostMapping("/edit")
    public ResponseEntity<?> editEmployee(UserEditDto userEditDto) {
        try {
            userService.editUser(userEditDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
