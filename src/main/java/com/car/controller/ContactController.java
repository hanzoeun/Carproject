package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ContactController {
	
	//문의하기 화면
		@GetMapping(value = "/contact")
		public String Contact() {
			return "contact/contact";
		}
	
}
