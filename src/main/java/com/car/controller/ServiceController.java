package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ServiceController {
	//서비스 화면
	@GetMapping( value = "/service")
	public String Service() {
		return "service/service";
	}
}
