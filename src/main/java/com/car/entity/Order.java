package com.car.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="order")
public class Order extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//기본키를 지정한다
	@Column(name ="OrderId")
	private Long id;
	
	@Column(nullable = false)
	private String lentStart; // 렌트 시작지역을 설명
	
	@Column(nullable = false)
	private String lentEnd; //렌트 반납지역을 설정
	
	@Enumerated(EnumType.STRING)
	private OrderStatus OrderStatus; //차량 구매확정 여부를 확인 
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, 
			orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Lent> lent = new ArrayList<>();
	
	public void addOrderLent(Lent lent) {
		this.lent.add(lent);
		lent.setOrder(this); //양방향 관계를 만든다. 
	}
}
