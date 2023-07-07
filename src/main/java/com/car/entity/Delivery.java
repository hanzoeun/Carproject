package com.car.entity;

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
import lombok.ToString;
import com.car.constant.*;


@Entity
@Getter
@Setter
@ToString
@Table(name = "delivery")
public class Delivery {

	@Id
	@Column(name = "delivery_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;
	
	@JoinColumn(name = "member_id")
	@ManyToOne
	private Member member;
	
	@JoinColumn(name = "lent_id")
	@ManyToOne
	private Lent lent;
	
	
}
