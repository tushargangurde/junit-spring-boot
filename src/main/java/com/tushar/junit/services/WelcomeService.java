package com.tushar.junit.services;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    public String getWelcomeMessage() {
        return "Welcome to Junit";
    }
}
