package com.quiz_platform.controller.response;

public class ApiResponse {
    private boolean error;
    private String message;
    public ApiResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
