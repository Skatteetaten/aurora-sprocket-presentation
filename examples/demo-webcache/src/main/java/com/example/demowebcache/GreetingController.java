package com.example.demowebcache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.Instant;

@RestController
public class GreetingController {

    private GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping("/hello")
    @Cacheable("hello")
    public GreetingResponse hello(@RequestParam(required = false) String name) throws InterruptedException {

        Thread.sleep(3000);
        GreetingResponse response = new GreetingResponse();
        response.greeting = greetingService.getGreeting(name);
        return response;
    }


    @RequestMapping("/greeter")
    @CacheEvict(cacheNames = "hello", allEntries = true)
    public void updateGreeter(@Valid @RequestBody UpdateGreeterPayload payload) {

        this.greetingService.setGreeter(payload.greeter);
    }
}

class GreetingResponse implements Serializable {

    String greeting;

    Instant createdAt = Instant.now();


    public String getGreeting() {

        return greeting;
    }


    public Instant getCreatedAt() {

        return createdAt;
    }
}

class UpdateGreeterPayload {

    String greeter;


    @NotNull
    @Pattern(regexp = "[A-z\\s]+")
    public String getGreeter() {

        return greeter;
    }


    public void setGreeter(String greeter) {

        this.greeter = greeter;
    }
}