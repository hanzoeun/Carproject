package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	//메인 화면
	@GetMapping(value = "/")
	public String Main() {
		return "/main";
	}
}