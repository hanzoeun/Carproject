package com.car.repository;

import org.springframework.data.domain.Page;

import com.car.entity.Car;

public interface CarRepositoryCustom {
	Page<Car> getAdminCarPage();
	
	
}
