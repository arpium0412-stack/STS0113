package com.maju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {

		@GetMapping("/admin/main.do")
		String admin() {		   
		  return "admin/main";
		}	
		
		@GetMapping("/manager/main.do")
		String manager() {			   
		  return "manager/main";
		}
		
		@GetMapping("/product/main.do")
		String product() {			   
		  return "product/main";
		}	

}
