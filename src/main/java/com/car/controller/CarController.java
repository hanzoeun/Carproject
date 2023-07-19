package com.car.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;






import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class CarController {


	
	@GetMapping(value = "/car")
	public String CarList() {
		
		return "car/car";
	}

	@GetMapping(value = "/cardatail")
	public String CarDetail() {
		return "car/carDetail";
	}

	@GetMapping(value = "/car/carlent")
	public String CarLent() {
		return "car/carLent";
	}

	@GetMapping(value = "/admin/caritem")
	public String Caritme(Model model) {
		
		return "/car/caritem";
	}


	
}
