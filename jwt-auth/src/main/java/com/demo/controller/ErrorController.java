package com.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handlerException(RuntimeException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handlerException(ResponseStatusException exception){
        return new ResponseEntity<>(exception.getReason(), HttpStatus.FORBIDDEN);
    }
}
