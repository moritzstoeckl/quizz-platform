package com.quiz_platform.controller;

import com.quiz_platform.authenticationdb.entity.User;
import com.quiz_platform.authenticationdb.service.UserService;
import com.quiz_platform.controller.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @Operation(
            summary = "Create New User",
            description = "Creates a new user with the specified username, password, and role."
    )
    public ResponseEntity<ApiResponse> createUser(
            @Parameter(description = "Username for the new user", required = true) @RequestParam String name,
            @Parameter(description = "Password for the new user", required = true) @RequestParam String password,
            @Parameter(description = "Role for the new user", required = false) @RequestParam(required = false) String role) {
        try {
            User.Role userRole;
            try {
                userRole = (role != null) ? User.Role.valueOf(role.toUpperCase()) : User.Role.STUDENT;
            } catch (IllegalArgumentException e) {
                ApiResponse errorResponse = new ApiResponse(true, "Invalid role specified: " + role);
                return ResponseEntity.ok(errorResponse);
            }

            userService.createUser(name, password, userRole);
            ApiResponse response = new ApiResponse(false, "User created successfully");
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            ApiResponse errorResponse = new ApiResponse(true, e.getMessage());
            return ResponseEntity.ok(errorResponse);
        }
    }

}
