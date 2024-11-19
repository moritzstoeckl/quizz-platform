package com.quiz_platform.controller;

import com.quiz_platform.authenticationdb.service.role.RequiresRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Example API", description = "API for demonstrating Swagger documentation")
public class QuizController {

    @Operation(
            summary = "Get a greeting message",
            description = "Returns a simple greeting message to confirm that the API is running",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully returned greeting message"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/")
    //TODO
    public String hello() {
        return "Hello, Spring Boot is running!";
    }


    @GetMapping("/testUserRole")
    @RequiresRole("TEACHER")
    @Operation(
            summary = "Test User Role Access",
            description = "Endpoint to verify that role-based access control is working as expected."
    )
    public ResponseEntity<String> testUserRole(@Parameter(description = "JWT token of the session to be terminated", required = true)
                                               @RequestParam String jwtToken) {
        // TODO: Remove test endpoint after the implementation is completed
        return ResponseEntity.ok("Hello, Authentication and role-based access control work!");
    }
}
