package com.example.demowebcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoWebcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebcacheApplication.class, args);
	}
}
