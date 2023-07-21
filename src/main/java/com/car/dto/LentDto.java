package com.car.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.car.constant.OrderStatus;
import com.car.entity.Car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LentDto {
			
	private Long id;
	
	private String lentName; //렌트 신청자이름 
	
	private int lentPrice;
	
	private String lentStart;
	
	private String lentEnd;
	
	private LocalDateTime orderDate; //주문일
	
	private OrderStatus orderStatus;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	//entity -> dto로 바꾼다.
	public static LentDto of(Car car) {
		return modelMapper.map(car, LentDto.class);
	}
	
	
	
}
