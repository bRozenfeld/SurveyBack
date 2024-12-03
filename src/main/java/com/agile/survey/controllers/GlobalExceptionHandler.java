package com.agile.survey.controllers;

import com.agile.survey.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {SurveyController.class})
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SurveyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSurveyNotFoundException(SurveyNotFoundException e) {
        logger.info("{} Survey not found: {}", this.getClass(), e.getMessage());
        e.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidSurveyDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSurveyDataException(InvalidSurveyDataException ex) {
        logger.info("{} Survey invalid : {}", this.getClass(), ex.getMessage());
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        logger.info("{} An exception occured : {}", this.getClass(), ex.getMessage());
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredSurveyException.class)
    public ResponseEntity<ErrorResponse> handleExpiredSurveyException(ExpiredSurveyException ex) {
        logger.info("{} Survey expired: {}", this.getClass(), ex.getMessage());
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyCompletedException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyCompletedException(AlreadyCompletedException ex) {
        logger.info("{} Survey already completed: {}", this.getClass(), ex.getMessage());
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeamSurveyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTeamSurveyNotFoundException(TeamSurveyNotFoundException ex) {
        logger.info("{} Team survey not found: {}", this.getClass(), ex.getMessage());
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
