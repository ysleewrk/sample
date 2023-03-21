package com.example.sample.common;

import org.springframework.http.HttpStatus;


public class ErrorResponse {

    private HttpStatus status;
    private String messages;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.messages = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessages() {
        return messages;
    }
}

