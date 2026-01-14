package com.maju.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.korea.member.Member;

public class SecurityUser extends User {

	private static final long serialVersionUID = 1L;

	public SecurityUser( Member vo ) {
		super(vo.getUsername(), 
			  vo.getPassword(), 
			  AuthorityUtils.createAuthorityList(vo.getRole().toString()));
  	}

}