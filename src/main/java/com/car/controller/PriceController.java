package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PriceController {
	
	// 가격 화면
	@GetMapping(value = "/price")
	public String Price() {
		return "price/price";
	}
}
