package com.quiz_platform.authenticationdb.response;

import java.time.LocalDateTime;

public class AuthResponse {
    private final String jwtToken;
    private final LocalDateTime expiresAt;

    public AuthResponse(String jwtToken, LocalDateTime expiresAt) {
        this.jwtToken = jwtToken;
        this.expiresAt = expiresAt;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
