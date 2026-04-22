package com.artur.clinicmanagmentapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PatientRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PatientErrorResponse> handleException(PatientNotFoundException exc){

        // create a PatientErrorResponse
        PatientErrorResponse error = new PatientErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // Return response enity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PatientErrorResponse> handleException(RuntimeException exc){

        // create a PatientErrorResponse
        PatientErrorResponse error = new PatientErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // Return response enity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
