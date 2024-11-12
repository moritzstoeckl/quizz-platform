package com.quiz_platform.controller.response;

import java.time.LocalDateTime;

public class AuthResponse extends ApiResponse{
    private final String jwtToken;

    public AuthResponse(String jwtToken, String message, boolean error) {
        super(error, message);
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
