package com.car.entity;



import com.car.constant.CarSellStatus;

import com.car.constant.CarType;
import com.car.dto.CarDto;



import groovy.transform.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "car")
@ToString
public class Car extends BaseEntity{ 
	//Entity는 db로 연결되는 곳이다 	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키를 지정해준다 
	@Column(name = "car_id")
	private Long id; // Car_id 식별자
	
	@Column(nullable = false)
	private String carName;  //car_name 차이름
	
	@Column(nullable = false, columnDefinition = "longtext")
	private String carDetail; //차량 상세설명
	
	@Column(nullable = false)
	private int carPrice; //차량 가격
	
	@Enumerated(EnumType.STRING)
	private CarSellStatus carSellStatus; //차량의 판매상태를 확인하는 것
	
	@Enumerated(EnumType.STRING)
	private CarType carType;  //차량 타입
	
	private String carYear; //차량 연식

	
	private int carKm; // 차량 km
	
	@ManyToOne
	@JoinColumn(name ="member_id")
	private Member member;
	
	

	
	
	
	
	
	
	//업데이트 쿼리문을 쓰지않고 엔티티값만 수정해준다. 
	public void updateItem(CarDto carDto) {
		this.carName = carDto.getCarName();
		this.carPrice = carDto.getCarPrice();
		this.carDetail = carDto.getCarDetail();
		this.carSellStatus = carDto.getCarSellStatus();
		this.carType = carDto.getCarType();
		this.carYear = carDto.getCarYear();
		this.carKm = carDto.getCarKm();
	}
	
	

	

	
	
}
