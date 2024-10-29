package com.quiz_platform.controller;

import com.quiz_platform.authenticationdb.service.UserService;
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
            description = "Creates a new user with the specified username and password."
    )
    public ResponseEntity<String> createUser(
            @Parameter(description = "Username for the new user", required = true) @RequestParam String name,
            @Parameter(description = "Password for the new user", required = true) @RequestParam String password) {
        userService.createUser(name, password);
        return ResponseEntity.ok("User created successfully");
    }
}
