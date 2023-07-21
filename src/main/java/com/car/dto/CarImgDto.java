package com.car.dto;

import org.modelmapper.ModelMapper;

import com.car.entity.CarImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarImgDto {
	private Long id;
	
	private String carName; //차량이름
	
	private String carNameOr; //차량 원본이미지 
	
	private String carImgUrl; //차량 이미지경로	
	
	private String carImgYn; //차량 대표이미지	
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static CarImgDto of(CarImg carImg) {
		return modelMapper.map(carImg, CarImgDto.class);
							// ModelMapper ( 1 , 2) 값을 통해서 각각 오브젝트값을 넣어준다
	}
	
	public void updateCarImg(String carName , String carNameOr , String carImgUrl ) {
		this.carName =carName;
		this.carNameOr =carNameOr;
		this.carImgUrl = carImgUrl;
	}
	
	
}

