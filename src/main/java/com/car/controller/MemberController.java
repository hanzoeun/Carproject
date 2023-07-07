package com.car.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.car.dto.MemberFormDto;
import com.car.entity.Member;
import com.car.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	
	// 로그인 화면
	@GetMapping(value = "/members/login")
	public String loginMember() {
		return "login/memberLoginForm";
	}
	
	
	// 회원가입
	@PostMapping(value = "/members/new")
	public String memberForm(@Valid MemberFormDto memberFormDto,
			BindingResult bindingResult , Model model) {
		// MemberFormDto -> Member Entity, 비밀번호 암호화
		// @Valid : 유효성을 검증하려는 객체 앞에 붙인다.
		// bindingResult: 유효성 검증후에 결과를 넣어준다.
		
		//에러가 있다면 회원가입 페이지로 이동
		if(bindingResult.hasErrors()) { //에러가 있다면 회원가입 페이지로 이동
			return "login/memberForm";
		}
		
		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
			
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage" , e.getMessage());
			return "login/memberForm";
		}

		return "redirect:/";
	}
	
	
	// 로그인 실패했을때
	@GetMapping(value = "/members/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "login/memberLoginForm";
	}
	
	
	// 회원가입 화면
		@GetMapping(value = "/members/new")
		public String memberForm(Model model) {
			model.addAttribute("memberFormDto", new MemberFormDto());
			return "login/memberForm";
		}
		
		

	
	
}
