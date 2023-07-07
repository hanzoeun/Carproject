package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
	
	
	//about 화면
	@GetMapping(value = "/contact/contact")
	public String Contact() {
		return "contact/contact";
	}
	
}
