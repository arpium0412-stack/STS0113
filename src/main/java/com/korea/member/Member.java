package com.korea.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name="member")
public class Member {
 // idx , userid, password, name, age, tel , addr , etc 
 
	@Id  // P.K 설정 (필수)  // 자동증가 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(length = 20, unique = true,  nullable = false)
	private String username;
	
	@Column(length = 100,nullable = false)
	private String password;
	
	@Column(length = 20,nullable = false)
	private String role;
	
	@Column(length = 20)
	private String name;
	
	@Column(length = 3)
	private int age;
	
	@Column(length = 14)
	private String tel;
		
	@Column(length = 500)
	private String etc;	
		
	@Column(length = 6 , name = "sample6_postcode")
	private String  sample6Postcode;
	
	@Column(length = 100 , name = "sample6_address")
	private String sample6Address;
	
	@Column(length = 50  , name = "sample6_detailAddress")
	private String sample6DetailAddress;
	
	@Column(length = 100 , name = "sample6_extraAddress")
	private String sample6ExtraAddress;
	
	@Transient
	private  String ch1;
	
	@Transient
	private  String ch2;

	
}
