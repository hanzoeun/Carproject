package com.car.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import com.car.dto.CarDto;
import com.car.dto.CarSearchDto;

import com.car.dto.MainCarDto;
import com.car.entity.Car;
import com.car.service.CarService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CarController {

	private final CarService carService;

	// 등록한 차량 전체 리스트
	@GetMapping(value = "/car")
	public String CarList(CarSearchDto carSearchDto, Optional<Integer> page, Model model) {
		Pageable pageabel = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<MainCarDto> cars = carService.getMainCarPage(carSearchDto, pageabel);

		model.addAttribute("cars", cars);
		model.addAttribute("carSearchDto", carSearchDto);
		model.addAttribute("maxPage", 5);

		return "car/car";
	}

	// 차량 상세페이지
	@GetMapping(value = "/cardatail")
	public String CarDetail() {
		return "car/carDetail";
	}

	// 상세설명
	@GetMapping(value = "/car/{carId}")
	public String CarLent(Model model, @PathVariable("carId") Long carId) {

		CarDto cardto = carService.getCarDto(carId);
		model.addAttribute("car", cardto);

		return "car/carlent";
	}

	// 차량 등록
	@GetMapping(value = "/admin/caritem")
	public String Caritme(Model model) {
		model.addAttribute("carDto", new CarDto());
		return "/car/caritem";
	}

	// 차량 이미지등록(insert)
	@PostMapping(value = "/admin/caritem")
	public String CarNew(@Valid CarDto carDto, BindingResult bindingResult, Model model,
			@RequestParam("carImgFile") List<MultipartFile> CarImgFileList) {
		if (bindingResult.hasErrors()) {
			return "car/caritem";
		}

		if (CarImgFileList.get(0).isEmpty()) {
			model.addAttribute("errorMessage", "첫번째 대표 이미지는 필수입니다.");
			return "car/caritem";
		}

		try {
			carService.saveCar(carDto, CarImgFileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "차량 등록 중 에러가 발생했습니다.");
			return "car/caritem";
		}

		return "redirect:/";
	}

	// 차량 관리 페이지
	@GetMapping(value = { "/admin/cars", "/admin/cars/{page}" })
	public String CarManage(CarSearchDto carSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
		// of(조회할 페이지의 번호 -> 0부터 시작, 한페이지당 조회할 데이터 갯수)
		// url경로에 페이지가 있으면 해당 페이지 번호를 조회하도록 하고 페이지 번호가 없으면 0페이지(첫번째 페이지)를 조회
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 3);
		
		Page<Car> cars = carService.getAdminCarPage(carSearchDto, pageable);
		
		model.addAttribute("cars" , cars);
		model.addAttribute("carSearchDto" , carSearchDto);
		model.addAttribute("maxPage" , 5); //관리페이지 하다넹 보여줄 최대 페이지 번호
		
		return "car/carMng";

	}

}
