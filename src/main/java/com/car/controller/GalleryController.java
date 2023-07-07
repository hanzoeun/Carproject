package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {
	
	
	//about 화면
	@GetMapping(value = "/gallery/gallery")
	public String  Gallery() {
		return "gallery/gallery";
	}
	
}
