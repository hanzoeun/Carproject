package com.car.repository;


import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import com.car.entity.Lent;

public interface LentRepository extends JpaRepository<Lent , Long>{

	@Query("select o from Lent o where o.member.email = :email order by o.orderDate desc")
	List<Lent> findlents(@Param("email")String email, Pageable pageable);
	
	
	
}
