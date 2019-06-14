package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    private GreetingService greetingService;


    public DemoApplication(GreetingService greetingService) {

        this.greetingService = greetingService;
    }


    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) {

        log.info(greetingService.getGreeting());
    }
}
