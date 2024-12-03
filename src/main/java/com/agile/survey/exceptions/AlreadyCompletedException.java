package com.agile.survey.exceptions;

public class AlreadyCompletedException extends RuntimeException {

    public AlreadyCompletedException(String message) {
        super(message);
    }
}
