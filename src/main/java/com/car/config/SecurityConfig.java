package com.car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration  //bean 객체를 싱글톤으로 만들어주는 어노테이션
@EnableWebSecurity //spring security filterChanin이 자동으로 포함되게 한다.
public class SecurityConfig {
		
	@Bean // 빈객체로 만들어준다 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//login crsf 추가? << 
		http.authorizeHttpRequests(authorize -> authorize  // 1. 페이지 접근 권한 
				//모든 사용자가 로그인(인증) 없이 접근할 수 있도록 설정 
				 .requestMatchers("/css/**","/js/**","/img/**","/images/**","/fonts/**").permitAll()   
				 .requestMatchers("/","/members/**","/item/**", "/security/**" , "/gallery/**" , "/contact/**" , "/services/**" , "/about/**").permitAll()
				 //"admin" 으로 시작하는 경로는 관리자만 접근가능하도록 설정
				 .requestMatchers("/admin/**").hasRole("ADMIN")
				 .requestMatchers("/favicon.ico", "/error").permitAll()
				 //그 외의 페이지는 모두 로그인을 받아야한다.
				 .anyRequest().authenticated()
				 )
		.formLogin(formLogin -> formLogin  //2. 로그인에 관련된 설정
				.loginPage("/members/login")  //로그인 페이지 URL설정 
				.defaultSuccessUrl("/") //로그인 성공시 이동할 페이지
				.usernameParameter("email")  // 로그인시 id로 사용할 파라메터 이름 
				.failureUrl("/members/login/error") //로그인 실패시 이동할 URL
				.permitAll()
				) 
		
		.logout(logout -> logout //3.로그아웃에 관련된 설정
				.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))  //로그아웃시 이동할 URL
				.logoutSuccessUrl("/") //로그아웃 성공시 이동할 URL
				.permitAll() 
				)		
		
		// 4. 인증되지 않은 사용자가 리소스에 접근했을때 설정 (ex. 로그인 안했는대 cart페이지에 접근..)
				 .exceptionHandling(handling -> handling
		      		   .authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
		         .rememberMe(Customizer.withDefaults());
				
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
