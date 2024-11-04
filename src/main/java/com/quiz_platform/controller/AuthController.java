package com.quiz_platform.controller;

import com.quiz_platform.controller.response.AuthResponse;
import com.quiz_platform.authenticationdb.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(
            summary = "User Login",
            description = "Authenticates a user with username and password, returning a JWT token on success."
    )
    public ResponseEntity<AuthResponse> login(@Parameter(description = "Username of the user", required = true) @RequestParam String username,
                                              @Parameter(description = "Password of the user", required = true) @RequestParam String password) {
        return authenticationService.login(username, password)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
    }


    @PostMapping("/logout")
    @Operation(
            summary = "User Logout",
            description = "Terminates the user's session based on the provided JWT token."
    )
    public ResponseEntity<Map<String, String>> logout(
            @Parameter(description = "JWT token of the session to be terminated", required = true)
            @RequestParam String jwtToken) {
        boolean success = authenticationService.logout(jwtToken);

        Map<String, String> response = new HashMap<>();
        if (success) {
            response.put("message", "Logged out successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid session");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


}

