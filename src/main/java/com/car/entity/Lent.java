package com.car.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.car.constant.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@Table(name = "lent")
public class Lent extends BaseEntity{
							//연결을 시켜서 시간등을 나타냄
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키로 지정을한다
	@Column(name ="lent_id")
	private Long id;
	
	private String lentName; //렌트 신청자이름 
	
	@Column(nullable = false)
	private int lentPrice; //렌트카 시간당 가격을 나타냄
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id")        
	private Car car;               
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	private String lentStart; //렌트 시작장소
	
	private String lentEnd; //렌트 반납장소
	
	
	 
	@Enumerated(EnumType.STRING)  //주문장소확인
	private OrderStatus orderStatus;
	
	private LocalDateTime orderDate; //주문일
	
	
	/* private List<Car> cars = new ArrayList<>(); */
	

	
	/*
	 * //lent 객체를 생성한다. public static Lent createLent(Member member , List<Car>
	 * carList) { Lent lent = new Lent(); lent.setMember(member);
	 * 
	 * for(Car car : carList) { car.addLent(lent); }
	 * 
	 * lent.setOrderStatus(OrderStatus.STAY);
	 * lent.setOrderDate(LocalDateTime.now());
	 * 
	 * return lent; }
	 * 
	 * 
	 * //주문취소 public void cancelOrder() { this.orderStatus = OrderStatus.CANCEL;
	 * 
	 * }
	 */
	
	
	
	

}
