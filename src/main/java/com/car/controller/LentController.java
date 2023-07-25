package com.car.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.dto.CarDto;
import com.car.dto.LentDto;
import com.car.service.LentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LentController {
		private final LentService lentService;
		
		
		
		@PostMapping(value ="/car")
		public @ResponseBody ResponseEntity lent(@RequestBody @Valid LentDto lentDto, BindingResult bindingResult, Principal principal) {
			if(bindingResult.hasErrors()) {
				StringBuilder sb = new StringBuilder();
				List<FieldError> fieldErrors = bindingResult.getFieldErrors();
				
				for(FieldError fieldError : fieldErrors) {
					sb.append(fieldError.getDefaultMessage()); //에러메세지를 합친다
				} 
				
				return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
			}
			
			String email = principal.getName(); //id에 해당하는 정보를 가지고온다
			Long LentId;
			
			try {
				LentId = lentService.lent(lentDto, email);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Long>(LentId, HttpStatus.OK); //성공 시 
		}
}
