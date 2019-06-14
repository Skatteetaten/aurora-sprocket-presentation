package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GreetingService {

    private String greeting;


    public GreetingService(@Value("${greeting:Hello}") String greeting) {

        this.greeting = greeting;
    }


    public String getGreeting() {

        return greeting;
    }
}
