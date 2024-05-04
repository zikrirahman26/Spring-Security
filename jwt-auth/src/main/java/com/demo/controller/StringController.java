package com.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StringController {
    
    @GetMapping("/welcome")
    public ResponseEntity<String> responseString(){
        String response = "Welcome to JWT Spring Security";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
