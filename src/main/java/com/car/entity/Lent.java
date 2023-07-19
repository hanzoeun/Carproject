package com.car.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Lent")
public class Lent extends BaseTimeEntity{
							//연결을 시켜서 시간등을 나타냄
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키로 지정을한다
	@Column(name ="lentId")
	private Long id;
	
	@Column(nullable = false)
	private int lentPrice; //렌트카 시간당 가격을 나타냄
	
	@ManyToOne
	@JoinColumn(name = "CarId")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name = "memberId")
	private Member member;
	
	
	@ManyToOne
	@JoinColumn(name = "OrderId")
	private Order order;
}
