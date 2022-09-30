package com.gradle.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class Application {

	 public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping
	public String Hello(){
		System.out.println("hi kyounghan!");
		return "2222hello!!!";
	}
}
