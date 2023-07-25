package com.car.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.car.dto.CarDto;
import com.car.dto.CarImgDto;
import com.car.dto.CarSearchDto;
import com.car.dto.MainCarDto;
import com.car.entity.Car;
import com.car.entity.CarImg;
import com.car.repository.CarImgRepository;
import com.car.repository.CarRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {
	private final CarRepository carRepository;
	private final CarImgRepository carImgRepository;
	private final CarImgService carImgService;
	
	
	
	//car 테이블에 상품등록하는 서비스
	public Long saveCar(CarDto cardto, List<MultipartFile> carImgFileList) throws Exception {
		
		
		Car car = cardto.createCar(); //dto -> Entity 로 변환 해준다 
		carRepository.save(car); // insert(저장) 한다 
		
		for(int i=0; i<carImgFileList.size(); i++) {
			CarImg carImg = new CarImg(); //객체 생성
			carImg.setCar(car); 
			
			if(i == 0) {
				carImg.setCarImgYn("Y");
			} else {
				carImg.setCarImgYn("N");
			}
			
			carImgService.saveCarImg(carImg, carImgFileList.get(i));
		}
		
		
		return car.getId(); //등록한 상품 id를 리턴해서 id값으로 상품이 나오게 만듬
	}
	//상품 가져오기
	@Transactional(readOnly = true)
	public CarDto getCarDto(Long carId) {
		List<CarImg> carImgList = carImgRepository.findByCarIdOrderByIdAsc(carId);
		
		List<CarImgDto> carImgDtoList = new ArrayList<>();
		for(CarImg carImg : carImgList) {
			CarImgDto carImgDto = CarImgDto.of(carImg);
			carImgDtoList.add(carImgDto);
		}
		
		Car car = carRepository.findById(carId)
								.orElseThrow(EntityNotFoundException::new);
		
		CarDto carDto = CarDto.of(car);
		
		carDto.setCarImgDtoList(carImgDtoList);
		
		return carDto;
	}
	
	
	// 수정하는 클래스 dto객체를 불러오고 이미지파일리스트를 파일업로드를 한다. 
	public Long updateCar(CarDto carDto, List<MultipartFile> carImgFileList) throws Exception {
		
		// 1. car엔티티를 가져와서 바꾼다.
		Car car = carRepository.findById(carDto.getId())
								.orElseThrow(EntityNotFoundException::new);
		
		
		//car update쿼리문 실행
		/*insert를 진행하면 엔티티가 영속성 컨텍스트에 저장이된다 
		 * 그 이후에는 변경감지 기능이 동작하기 때문에 개발자는 update쿼리문을 쓰지 않고
		 * 엔티티를 변경해준다.
		 * 
		 * */
		car.updateItem(carDto);
		
		List<Long> carImgIds = carDto.getCarImgs();
		
		for(int i=0; i<carImgFileList.size(); i++) {
			carImgService.updateCarImg(carImgIds.get(i), carImgFileList.get(i));
		}
		
		return car.getId();
	}
	
	@Transactional(readOnly = true)
	public Page<Car> getAdminCarPage(CarSearchDto carSearchDto, Pageable pageable) {
		Page<Car> carPage = carRepository.getAdminCarPage(carSearchDto, pageable);
		return carPage;
	}
	
	
	@Transactional(readOnly = true)
	public Page<MainCarDto> getMainCarPage(CarSearchDto carSearchDto, Pageable pageable) {
		Page<MainCarDto> mainCarPage = carRepository.getMainCarPage(carSearchDto, pageable);
		
		return mainCarPage;
	}
}
