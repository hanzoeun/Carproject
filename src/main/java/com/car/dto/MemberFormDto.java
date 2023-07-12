package com.car.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberFormDto {
	@NotBlank(message = "이름은 필수입력 값입니다.")
	private String name;  //이름
	
	@NotEmpty(message = "이메일은 필수입력 값입니다.")
	@Email(message = "이메일 형식으로 입력해주세요.")  //유효성 체크를 자동적으로 해주는 어노테이션 
	private String email;  //이메일
	
	@NotEmpty(message = "비밀번호는 필수입력 값입니다.")
	@Length(min = 8 , max = 16 , message = "비밀번호는 8자~16자 사이로 입력해주세요.")
	private String password; //비밀번호
	
	@NotEmpty(message = "주소는 필수입력 값입니다.")
	private String address;  //
	
	@NotEmpty(message = "핸드폰번호는 필수입력 값입니다.")
	private String phone;
}