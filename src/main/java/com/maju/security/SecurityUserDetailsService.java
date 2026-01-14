package com.maju.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.korea.member.Member;
import com.korea.member.MemberRepository;


@Service
public class SecurityUserDetailsService implements UserDetailsService  {
	
	SecurityUserDetailsService(){
		System.out.println("==>SecurityUserDetailsService");
	}
	
	@Autowired
	MemberRepository service;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member vo = new Member();
		System.out.println("username확인:" + username);
		vo.setUsername(username);
		
		Member user = service.findByUsername(username);
		if (user == null) {
			throw  new UsernameNotFoundException(username + "사용자 없음");
		}else {
			return new SecurityUser(user);
		}
	}

}
