package com.quiz_platform.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String hello() {
        return "Hello, Spring Boot is running!";
    }
}
