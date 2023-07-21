package com.car.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.car.constant.CarSellStatus;
import com.car.dto.CarSearchDto;
import com.car.dto.MainCarDto;
import com.car.dto.QMainCarDto;
import com.car.entity.Car;
import com.car.entity.QCar;
import com.car.entity.QCarImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class CarRepositoryCustomImpl implements CarRepositoryCustom {

	private JPAQueryFactory queryFactory;

	public CarRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	
	//현재 날짜로부터 이전날짜를 구해주는 메소드
	//현재 날짜로부터 이전날짜를 구해주는 메소드
	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now(); //현재 날짜, 시간
		
		if(StringUtils.equals("all", searchDateType) || searchDateType == null) 
			return null;
		else if(StringUtils.equals("1d", searchDateType))
			dateTime = dateTime.minusDays(1); //현재 날짜로부터 1일전
		else if(StringUtils.equals("1w", searchDateType))
			dateTime = dateTime.minusWeeks(1); //1주일 전
		else if(StringUtils.equals("1m", searchDateType))
			dateTime = dateTime.minusMonths(1); //1달전
		else if(StringUtils.equals("6m", searchDateType))
			dateTime = dateTime.minusMonths(6); //6개월전
		
		return QCar.car.startTime.after(dateTime); //Q객체 리턴
	}
	
	//상태를 전체로 했을때 null이 들어있으므로 처리를 한번 해준다
	private BooleanExpression searchSellStatusEq(CarSellStatus searchSellStatus) {
		return searchSellStatus == null ? null : 
			QCar.car.carSellStatus.eq(searchSellStatus);
	}
	
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("itemNm", searchBy)) {
			//등록자로 검색시
			return QCar.car.carName.like("%"+ searchQuery +"%"); //item_nm like %검색어%
		} else if(StringUtils.equals("createdBy", searchBy)) {
			return QCar.car.createdBy.like("%"+ searchQuery +"%"); //create_by like %검색어%
		}
		
		return null; //쿼리문을 실행하지 않는다.
	}

	@Override
	public Page<Car> getAdminCarPage(CarSearchDto carSearchDto, Pageable pageable) {
		
		/*
		 * select * from item where reg_time = ? 
		 * and item_sell_status = ? and item_nm(create_by) like %검색어% 
		   order by item_id desc;
		 */
		
		List<Car> content = queryFactory 
				.selectFrom(QCar.car)
				.where(regDtsAfter(carSearchDto.getSearchDateType()), 
					   searchSellStatusEq(carSearchDto.getSearchSellStatus()),
					   searchByLike(carSearchDto.getSearchBy(), carSearchDto.getSearchQuery()))
				.orderBy(QCar.car.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
				
		
		/* select count(*) from item where reg_time = ? 
		 * and item_sell_status = ? and item_nm(create_by) like %검색어%
	     */
		long total = queryFactory.select(Wildcard.count).from(QCar.car)
				.where(regDtsAfter(carSearchDto.getSearchDateType()), 
						   searchSellStatusEq(carSearchDto.getSearchSellStatus()),
						   searchByLike(carSearchDto.getSearchBy(), carSearchDto.getSearchQuery()))
				.fetchOne();
		
		
		return new PageImpl<>(content, pageable, total);
	}
	
	//검색어가 빈문자열 일때를 대비해
	private BooleanExpression carNameLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ? 
				null : QCar.car.carName.like("%" + searchQuery + "%");
	}

	@Override
	public Page<MainCarDto> getMainCarPage(CarSearchDto carSearchDto, Pageable pageable) {
		/* select item.id, item.itemNm, item.itemDetail, item_img.imgUrl, item.price 
		 *    from item, item_img 
		 *    where item.item_id = item_img.item_id
		 *    and item_img.repimg_yn = 'Y'
		 *    and item.item_nm like '%검색어%'
		 * order by item.item_id desc;  
		 * */
		
		QCar car = QCar.car;
		QCarImg carImg = QCarImg.carImg;
		
		//dto로 객체로 바로 받아올 때는 
		//1.컬럼과 dto객체의 필드가 일치해야 한다.
		//2.dto객체의 생성자에 @QueryProjection를 반드시 사용해야 한다.
		List<MainCarDto> content = queryFactory
				.select(
					new QMainCarDto(
						car.id,
						car.carName,
						car.carDetail,
						carImg.carImgUrl,
						car.carPrice)	
				)
				.from(carImg)
				.join(carImg.car, car)
				.where(carImg.carImgYn.eq("Y"))
				.where(carNameLike(carSearchDto.getSearchQuery()))
				.orderBy(car.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory
				.select(Wildcard.count)
				.from(carImg)
				.join(carImg.car, car)
				.where(carImg.carImgYn.eq("Y"))
				.where(carNameLike(carSearchDto.getSearchQuery()))
				.fetchOne();
		
		return new PageImpl<>(content, pageable, total);
	}







}
