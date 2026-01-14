package com.maju.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	SecurityConfig(){
		System.out.println("==>SecurityConfig");
	}
	
	@Autowired
	SecurityUserDetailsService	securityUserDetail;
	
	@Bean
	SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
		
		// hasRole : 하나의 역할을 지정할때.
		// hasAnyRole : hasAnyRole("MANAGER", "ADMIN") - 여러 역할을 지정할때.
		// 1. "/chatGPT/**" 와 "/product/productForm" 는 관리자만 접근
		// 2. "/product/cartList" 는 ADMIN 와 MEMBER만 접근
		// 3. "/product/**" 는 인증받은 모든 사람이 접근

		http.authorizeHttpRequests(authorize -> authorize	              
	              .requestMatchers("/admin/**").hasRole("ADMIN")
	              .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")	 
	              .requestMatchers("/product/**").authenticated()
	              .anyRequest().permitAll())
				
		.csrf(csrf ->csrf.disable())	
	    
		.formLogin(login ->login.loginPage("/member/login.do").defaultSuccessUrl("/member/loginSuccess.do", true))
		.exceptionHandling(handling ->handling.accessDeniedPage("/member/accessDenied.do"))
		.logout(logout ->logout.invalidateHttpSession(true).logoutSuccessUrl("/"))
		.userDetailsService(securityUserDetail);
		return http.build(); 
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();		
	}
}