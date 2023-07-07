package com.car.entity;

import java.time.LocalDateTime;

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

@Entity
@Table(name = "lent")
@Getter
@Setter
@ToString
public class Lent {
	
	@Id
	@Column(name = "lent_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDateTime lentDateStart;
	
	private LocalDateTime lentDateEnd;
	
	private int lentPrice; 
	
	@ManyToOne
	@JoinColumn(name ="member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
}
