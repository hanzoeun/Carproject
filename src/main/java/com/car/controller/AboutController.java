package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
	
	//about 화면
	@GetMapping(value = "/about/about")
	public String About() {
		return "about/about";
	}
	
}