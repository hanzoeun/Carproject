package com.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.entity.CarImg;



public interface CarImgRepository extends JpaRepository<CarImg, Long>{
	//select * from car_img where car_id = ? order by car_id asc;
	List<CarImg> findByCarIdOrderByIdAsc(Long carId);
	
	//select * from car_img where car_id ? and car_img_yn = ? 
	CarImg findByCarIdAndCarImgYn(Long carId , String carImgYn);
	
	
	
	
}
