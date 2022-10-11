package com.gradle.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
@SpringBootApplication
public class Application {

	 public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@RequestMapping
//	public String Hello(){
//		System.out.println("hi kyounghan!");
//		return "2222hello!!!";
//	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Hello(Model model){
		System.out.println("hi kyounghan!");
		model.addAttribute("hello", "서버에서 보내준 값 입니다.");
		return "/hello";
	}
}
