package com.car.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.car.dto.CarSearchDto;
import com.car.dto.MainCarDto;
import com.car.service.CarService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MainController {
	
	
	private final CarService carService;
	
	//메인 화면 
	@GetMapping(value = "/")
	public String Main(CarSearchDto carSearchDto,  Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<MainCarDto> cars = carService.getMainCarPage(carSearchDto, pageable);
		
		
		model.addAttribute("cars" , cars);
		model.addAttribute("carSearchDto" , carSearchDto);
	
		return "/main";
	}
}
