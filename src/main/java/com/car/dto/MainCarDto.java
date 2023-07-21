package com.car.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainCarDto {
	private Long id;
	
	private String carName;
	
	private String carDetail;
	
	private String carImgUrl;
	
	private Integer carPrice;
	
	
	@QueryProjection //생성자를 통해 dto를 조회하는 방법을 가진다 
	public MainCarDto(Long id , String carName , String carDetail , String carImgUrl, Integer carPrice) {
		this.id =id ;
		this.carName =carName;
		this.carDetail = carDetail;
		this.carImgUrl =carImgUrl;
		this.carPrice = carPrice;
	}
}
