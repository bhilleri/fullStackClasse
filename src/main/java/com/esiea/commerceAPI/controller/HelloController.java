package com.esiea.commerceAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/helloworld")
	public String getHelloWorld() {
		return "HelloWorld";
	}
	
}
