package com.car.entity;

import com.car.constant.CarSellStatus;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "car")
@ToString
public class Car {
	//Entity는 db로 연결되는 곳이다 	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키를 지정해준다 
	@Column(name = "CarId")
	private Long id; // Car_id 식별자
	
	@Column(nullable = false)
	private String CarName;  //Car_name 차이름
	
	@Column(nullable = false, columnDefinition = "longtext")
	private String CarDetail; //차량 상세설명
	
	@Column(nullable = false)
	private int CarPrice; //차량 가격
	
	@Enumerated(EnumType.STRING)
	private CarSellStatus carSellStatus; //차량의 판매상태를 확인하는 것
}
