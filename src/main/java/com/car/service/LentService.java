package com.car.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.dto.CarDto;
import com.car.entity.Car;
import com.car.entity.Member;
import com.car.repository.CarImgRepository;
import com.car.repository.CarRepository;
import com.car.repository.LentRepository;
import com.car.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LentService {
	private final CarRepository carRepository;
	private final MemberRepository memberRepository;
	private final LentRepository lentRepository;
	private final CarImgRepository carImgRepository;
	
	/*
	 * public Long lent(CarDto carDto, String email) {
	 * 
	 * //1. 주문할 상품을 조회 Car car =
	 * carRepository.findById(carDto.getId()).orElseThrow(EntityNotFoundException::
	 * new);
	 * 
	 * 
	 * //2.현재 로그인한 회원의 이메일을 이용해 회원 정보를 조회 Member member =
	 * memberRepository.findByEmail(email);
	 * 
	 * //3.주문할 상품 엔티티와 주문 수량을 이용하여 주문 상품 엔티티를 생성 List<Car> carList = new
	 * ArrayList<>();
	 * 
	 * }
	 */
}
