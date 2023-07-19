package com.car.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;


import com.car.constant.CarSellStatus;
import com.car.entity.Car;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {
	
	
	private Long id;
	
	@NotBlank(message = "차랑명은 필수 입력입니다.")
	private String CarName;
	
	@NotBlank(message = "차량상세설명은 필수 입력입니다.")
	private String CarDetail;
	
	@NotBlank(message = "차량가격은 필수 입력입니다.")
	private int CarPrice;
	
	private CarSellStatus carSellStatus;
	
	private List<Long> CarImgs = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Car createCar() {
		return modelMapper.map(this, Car.class);
	}
}
