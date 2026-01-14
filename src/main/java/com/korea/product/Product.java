package com.korea.product;

import org.springframework.web.multipart.MultipartFile;

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
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(length = 2, nullable = false)
	private String category;
	
	@Column(length = 10, unique = true, nullable = false)
	private String pid;
	
	@Column(length = 100, nullable = false)
	private String pname;
	
	private int pprice;
	private String pdesc;
	private int pquantity;
	
	@Transient
	private MultipartFile pfile;
	private String pimgname;
	
	// 상품 관리자
	private String padmin;
	// 상품 등록일
	private String today;
	
	@Transient
	private  String ch1;
	
	@Transient
	private  String ch2;
}
