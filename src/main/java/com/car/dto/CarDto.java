package com.car.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.car.constant.CarSellStatus;

import com.car.constant.CarType;
import com.car.entity.Car;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {

	private Long id;

	@NotBlank(message = "차랑명은 필수 입력입니다.")
	private String carName; // 차량명

	@NotBlank(message = "차량상세설명은 필수 입력입니다.")
	private String carDetail; //차량 상세설명

	@NotNull(message = "가격은 필수 입력입니다.")
	private int carPrice; //차량 가격

	private CarSellStatus carSellStatus;

	private CarType carType;  //차종

	private String carYear; //연식
	
	private List<CarImgDto> carImgDtoList = new ArrayList<>(); //차 이미지를 리스트로 넣는다

	
	@NotNull(message = "주행거리는 필수 입력입니다.")
	private int carKm;  //주행거리

	private List<Long> carImgs = new ArrayList<>();  

	private static ModelMapper modelMapper = new ModelMapper();
	
	
	
	
	
	/*
	CarDto carDto = 레퍼지토리에서 가져옴;
	cardto.addCarImg()
	*/
	
	//entity 를 dto로 변환시킨다
	public Car createCar() {
		return modelMapper.map(this, Car.class);
	}
	
	
	//dto -> entity 객체를 만든다
	public static CarDto of(Car car) {
		return modelMapper.map(car, CarDto.class);
	}
	
	// 현재 DTO(CarDto) 의 carImgDtoList에 CarImgDto를 넣어줌.
	public void addCarImg(CarImgDto carImgDto) {
		this.carImgDtoList.add(carImgDto);
	}
}
