package com.example.demoweb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GreetingService {

    private String greeting;

    private String greeter = "Bjarte";

    public GreetingService(@Value("${greeting:Hello}") String greeting) {

        this.greeting = greeting;
    }

    public String getGreeting(String name) {

        if ("Bent".equals(name)) {
            throw new IllegalArgumentException("name cannot be Bent");
        }

        return String.format("Hello, %s, from %s", name, greeter);
    }

    public void setGreeter(String greeter) {
        this.greeter = greeter;
    }
}
