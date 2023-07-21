package com.car.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.car.constant.OrderStatus;
import com.car.entity.Lent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LentOrderDto {
	
	//entity -> dto로 변환 
	public LentOrderDto(Lent lent) {
		this.LentId = lent.getId();
		this.orderDate = lent.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.orderStatus = lent.getOrderStatus();
	}
	
	
	private Long LentId; //주문 아이디
	
	private String orderDate; //주문날짜
	
	private OrderStatus orderStatus; //주문상태
	
	private List<CarDto> carDtoList = new ArrayList<>(); //carDto를 리스트로 만듬 
	
	
	//CarDto 객체를 리스트에 넣어주는 메소드 
	public void addCarDto(CarDto carDto) {
		this.carDtoList.add(carDto);
	}
	
	
}
