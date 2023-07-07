package com.car.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "Car")
@ToString
public class Car {
	
	@Id
	@Column(name = "car_id")
	@GeneratedValue(strategy = GenerationType.AUTO) //프라이버리 키지정
	private Long id;
	
	private String CarModel; //차이름
	
	private String CarColor; //차색상
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String CarCompany; //제조사
	
	private int CarPrice; //차가격
	
	private String CarDetail; // 차 상세정보
	
	private String CarBooth; // 전시장 정보
	
}
