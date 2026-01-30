package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	Thread thread = new Thread();
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
