package com.car.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.car.dto.CarSearchDto;
import com.car.dto.MainCarDto;
import com.car.entity.Car;

public interface CarRepositoryCustom {
	Page<Car> getAdminCarPage(CarSearchDto carSearchDto, Pageable pageable); //페이지에 검색을 할수있게 해주는 쿼리문
	
	Page<MainCarDto> getMainCarPage(CarSearchDto carSearchDto, Pageable pageable);
}
