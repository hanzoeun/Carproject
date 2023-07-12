package com.car.entity;

import org.springframework.security.crypto.password.PasswordEncoder;



import com.car.constant.Role;
import com.car.dto.MemberFormDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="members")
@ToString
public class Member {
	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Column(unique = true) //중복된 값이 들어올 수 없다
	private String email;
	
	private String password;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	

	private String phone;
	
	
	//MemberFormDto를 -> Member 엔티티 객체로 변환 
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		//패스워드 암호화
		String password = passwordEncoder.encode(memberFormDto.getPassword());
			
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setAddress(memberFormDto.getAddress());
		member.setPassword(password);
		member.setPhone(memberFormDto.getPhone());
//		member.setRole(Role.ADMIN);  //관리자로 가입할때
		member.setRole(Role.USER); //일반사용자로 가입할때
		
		return member;
	}
}