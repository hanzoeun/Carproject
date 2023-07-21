package com.car.service;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.car.entity.CarImg;
import com.car.repository.CarImgRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CarImgService {

	private String CarImgLocation = "C:/Car/Caritem";
	private final CarImgRepository carImgRepository;
	private final FileService fileService;
	
	/*이미지를 저장
	 * 1. 파일 이미지를 저장해준다. 
	 * 2.Car에 저장을한다
	 * */
	
	public void saveCarImg(CarImg carImg , MultipartFile carImgFile )throws Exception {
		String carNameOr = carImgFile.getOriginalFilename(); //-> 파일이름
		String carName = "";
		String carImgUrl = "";
		
		
		//1. 파일을 carimglocation에 저장을 한다.
		if(!StringUtils.isEmpty(carNameOr)) {
			
			//carnameor이 빈문자열이 아니라면 이미지파일을 업로드한다.
			carName = fileService.uploadFile(CarImgLocation, carNameOr, carImgFile.getBytes());
			carImgUrl = "/images/Car" + carName;
		}
		
		
		//2.car_img를 테이블에 저장한다.
		carImg.updateItemImg(carNameOr, carName, carImgUrl);
		carImgRepository.save(carImg); // db에 insert는 하는방법
	}
	
	public void updateCarImg(Long carImgId, MultipartFile carImgFile) throws Exception {
		
		if(!carImgFile.isEmpty()) {
			
			CarImg savedCarImg = carImgRepository.findById(carImgId)
												.orElseThrow(EntityNotFoundException::new);
			
			if(!StringUtils.isEmpty(savedCarImg.getCarName())) {
				fileService.deleteFile(CarImgLocation + "/" + savedCarImg.getCarName());
			}
			
			String carNameOr = carImgFile.getOriginalFilename();
			String carName = fileService.uploadFile(CarImgLocation, carNameOr, carImgFile.getBytes());
			String carImgUrl = "/images/caritem" + carName;
			
			savedCarImg.updateItemImg(carNameOr, carName, carImgUrl);
		}
		
	}
}
