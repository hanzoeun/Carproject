package com.car.controller;





import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.car.dto.CarDto;
import com.car.dto.CarSearchDto;

import com.car.dto.MainCarDto;
import com.car.service.CarService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class CarController {

	private final CarService carService;
	
	
	//등록한 상품 리스트
	@GetMapping(value = "/car")
	public String CarList(CarSearchDto carSearchDto, Optional<Integer> page, Model model) {
		Pageable pageabel = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<MainCarDto> cars = carService.getMainCarPage(carSearchDto, pageabel);
		
		model.addAttribute("cars" , cars);
		model.addAttribute("carSearchDto" , carSearchDto);
		model.addAttribute("maxPage", 5);
		
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
		model.addAttribute("carDto" , new CarDto());
		return "/car/caritem";
	}
	
	@PostMapping(value = "/admin/caritem")
	public String CarNew(@Valid CarDto carDto , BindingResult bindingResult ,
			Model model , @RequestParam("carImgFile") List<MultipartFile> CarImgFileList) {
		if(bindingResult.hasErrors()) {
			return "car/caritem";
		}
		
		if(CarImgFileList.get(0).isEmpty()) {
			model.addAttribute("errorMessage" , "첫번째 대표 이미지는 필수입니다.");
			return "car/caritem";
		}
		
		try {
			carService.saveCar(carDto, CarImgFileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage" , "차량 등록 중 에러가 발생했습니다.");
			return "car/caritem";
		}
		
		return "redirect:/";
	}


	/*
	 * @PostMapping(value = "/car") public @ResponseBody ResponseEntity<String>
	 * Car(@RequestBody @Valid LentDto lentDto , BindingResult bindingResult,
	 * Principal principal) {
	 * 
	 * if(bindingResult.hasErrors()) { StringBuilder sb = new StringBuilder();
	 * List<FieldError> fieldErrors = bindingResult.getFieldErrors();
	 * 
	 * for(FieldError fieldError : fieldErrors) {
	 * sb.append(fieldError.getDefaultMessage()); //에러메세지를 합친다. }
	 * 
	 * return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST); }
	 * 
	 * String email = principal.getName(); //id에 해당하는 정보를 가지고온다(email) Long orderId;
	 * 
	 * try { orderId = carService. } }
	 */
}
