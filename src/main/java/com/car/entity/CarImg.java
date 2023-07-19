package com.car.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;


@Setter
@Getter
@ToString
@Entity
@Table(name = "CarImg")
public class CarImg extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키를 지정한다
	@Column(name = "CarImgId")
	private Long id; 
	
	

	private String CarName; //바뀐 차량이미지 이름을 나타낸다 
	
	
	
	private String CarNameOr;  //원본 차량 이미지 이름을 나타낸다
	

	private String CarImgUrl; //차량 이미지 경로를 확인한다
	

	private String CarImgYn; // 대표이미지를 넣는지
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CarId")
	private Car car;
	
	
	//이미지에 대한 정보를 업데이트 하는 메소드
		public void updateItemImg(String CarNameOr, String CarName, String CarImgUrl) {
			this.CarNameOr = CarNameOr;
			this.CarName = CarName;
			this.CarImgUrl = CarImgUrl;
		}
}
