package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	
	//security 화면
	@GetMapping(value = "/security/board")
	public String Security() {
		return "security/securityForm";
	}
	
	
}
