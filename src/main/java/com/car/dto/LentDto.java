package com.car.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.car.constant.OrderStatus;
import com.car.entity.Car;
import com.car.entity.Lent;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LentDto {
			
	private Long id;
	
	@NotBlank(message = "신청자 명은 필수 입력입니다.")
	private String lentName; //렌트 신청자이름 
	
	private int lentPrice;
	
	private String lentEStarts;  //렌트 시작장소
	
	private String lentEEnds; //렌트 반납장소 
	
	private LocalDateTime orderDate; //주문일
	
	private OrderStatus orderStatus;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	//entity -> dto로 바꾼다.
	public static LentDto of(Car car) {
		return modelMapper.map(car, LentDto.class);
	}
	
	
	
	public Lent createLent() {
		return modelMapper.map(this, Lent.class);
	}
	
	
	
}
