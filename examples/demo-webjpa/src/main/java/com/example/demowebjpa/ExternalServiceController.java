package com.example.demowebjpa;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/external")
public class ExternalServiceController {

    private RestTemplate restTemplate;

    public ExternalServiceController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/echo1/{param}")
    public Map echo(@PathVariable String param) {

        return restTemplate.getForEntity("https://httpbin.org/anything/" + param, Map.class).getBody();
    }

    @GetMapping("/echo2/{param}")
    public Map echo2(@PathVariable String param) {

        return restTemplate.getForEntity("https://httpbin.org/anything/{0}", Map.class, param).getBody();
    }
}
