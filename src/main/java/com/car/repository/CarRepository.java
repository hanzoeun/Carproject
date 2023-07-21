package com.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.constant.CarSellStatus;
import com.car.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> , CarRepositoryCustom {

	// select * from car where car_name = ?
	List<Car> findByCarName(String carName); // 차량을 검색할때 쓰는 쿼리문

	// select * from car where car_name = ? and car_sell_status = ?
	List<Car> findByCarNameAndCarSellStatus(String carName, CarSellStatus carSellStatus); 
	// 차량의 이름과 차량이 현재 판매중인지  확인하는쿼리문
																							// 확인하는쿼리문
	
}
