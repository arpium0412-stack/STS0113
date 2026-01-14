package com.maju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.korea.product.Product;
import com.korea.product.ProductRepository;
import com.korea.product.S3UploaderService;

@RequestMapping("/product/")
@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository  productRepo;
	
	@Autowired
	private S3UploaderService  s3UploaderService;
	
	@GetMapping("form.do")
	String form() {
	   System.out.println("==>/product/form.do");
	return "product/form";
	}
	
	@PostMapping("formOK.do")
	String formOK(Product product) {
	    System.out.println("==>/product/formOK.do");
	    Long nextIdx = productRepo.findMaxIdx();
	    product.setPid(product.getCategory() + nextIdx);
	    
	 // S3 파일 업로드
	    String fileName = s3UploaderService.uploadFile(product.getPfile());
	    product.setPimgname(fileName);
	 		
	    productRepo.save(product);
	    return "redirect:/product/list.do";
	}
	
	@GetMapping("list.do")
	String list(Model  model) {
	   System.out.println("==>/member/list.do");
	   model.addAttribute("li", productRepo.findAllByOrderByIdxDesc());	   
	return "product/list";
	}

}
