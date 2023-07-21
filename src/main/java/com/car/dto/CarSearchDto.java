package com.car.dto;

import com.car.constant.CarSellStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarSearchDto {    // 차량을 찾는 dto 
	private String searchDateType;
	private CarSellStatus searchSellStatus;
	private String searchBy;
	private String searchQuery = "";
}
