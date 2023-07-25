package com.car.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.dto.CarDto;
import com.car.dto.LentDto;
import com.car.dto.LentHistDto;
import com.car.entity.Car;
import com.car.entity.Lent;
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
	
	
	//주문하기
	public Long lent(LentDto lentDto , String email) {
		Car car = carRepository.findById(lentDto.getId()).orElseThrow(EntityNotFoundException::new);
		
		Member member = memberRepository.findByEmail(email);
		
		List<Lent> lentList = new ArrayList<>();
		
		Lent lent = Lent.createLent(member, lentList);
		lentRepository.save(lent); //insert?
		
		return lent.getId();
	}
	

	 

}
