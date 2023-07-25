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
public class LentHistDto {
	public LentHistDto(Lent lent) {
		this.lentId = lent.getId();
		this.orderDate = lent.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy--MM--dd HH:mm"));
		this.orderStatus = lent.getOrderStatus();
	}
	
	private Long lentId;
	private String orderDate;
	private OrderStatus orderStatus;
	private List<CarDto> carDtoList = new ArrayList<>();
	
	
	//CarDto 객체를 주문상품 리스트에 추가하는 메소드
	public void addCarDtO(CarDto carDto) {
		this.carDtoList.add(carDto);
	}
}
