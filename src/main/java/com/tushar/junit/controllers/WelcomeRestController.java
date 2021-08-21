package com.tushar.junit.controllers;

import com.tushar.junit.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {

    @Autowired
    private WelcomeService welcomeService;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeMessage() {
        String welcomeMessage = welcomeService.getWelcomeMessage();
        return new ResponseEntity<String>(welcomeMessage, HttpStatus.OK);
    }
}
