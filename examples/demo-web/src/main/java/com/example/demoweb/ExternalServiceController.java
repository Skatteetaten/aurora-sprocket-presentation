package com.example.demoweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/external")
public class ExternalServiceController {

    private RestTemplate restTemplate;


    public ExternalServiceController(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }


    @GetMapping
    public Map echo() {

        return restTemplate.getForEntity("https://httpbin.org/anything/test", Map.class).getBody();
    }
}
