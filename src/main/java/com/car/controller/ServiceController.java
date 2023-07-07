package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {
	
	//about 화면
	@GetMapping(value = "/services/services")
	public String  Service() {
		return "services/services";
	}
	
}
