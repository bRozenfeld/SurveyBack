package com.agile.survey.controllers;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;

    private String timestamp;

    private int status;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }
}
