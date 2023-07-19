package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
			
	//회사소개 화면
		@GetMapping(value = "/about")
		public String about() {
			return "about/about";
		}
		
	
}
