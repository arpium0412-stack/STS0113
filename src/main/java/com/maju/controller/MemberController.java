package com.maju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.member.Member;
import com.korea.member.MemberRepository;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/member/")
@Controller
public class MemberController {
	
    @Autowired
	private MemberRepository  memberRepo;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@GetMapping("form.do")
	String main() {
	   System.out.println("==>/member/form.do");
	return "member/form";
	}
	
	@PostMapping("formOK.do")
	String formOK(Member member) {
	   System.out.println("==>/member/formOK.do");
	   
	   String  password  = passwordEncoder.encode(member.getPassword());	   
	   member.setPassword(password);
	   
	   memberRepo.save(member);
	   	   
	return "redirect:/member/list.do";
	}


	@GetMapping("delete.do")
	String delete(Member member) {
	   memberRepo.deleteById(member.getIdx());	   	   
	return "redirect:/member/list.do";
	}
		
	@GetMapping("idCheck.do")
	@ResponseBody
	public String idCheck(@RequestParam String username) {
		 System.out.println("==>/member/idCheck.do");
	    return memberRepo.existsByUsername(username)?"T":"F";
	}
	
	@GetMapping("list.do")
	String list(Model  model , Member  member) {
	   System.out.println("==>/member/list.do");
	   
	   if ("name".equals(member.getCh1())) {
		   model.addAttribute("li",memberRepo.findAllByNameContainingOrderByIdxDesc(member.getCh2()));
	   }else if("sample6Address".equals(member.getCh1())) {
		   model.addAttribute("li",memberRepo.findAllBySample6AddressContainingOrderByIdxDesc(member.getCh2()));
	   }else {
		   model.addAttribute("li", memberRepo.findAllByOrderByIdxDesc());   
	   }
	   
	   
	return "member/list";
	}
	
	@GetMapping("edit.do")
	String edit(Model  model , Member member) {
	   Member  m = memberRepo.findById(member.getIdx()).orElseThrow(() -> new RuntimeException("회원 없음"));
	   model.addAttribute("m", m);
	return "member/edit";
	}
	
	@PostMapping("update.do")
	String update(Member member) {
	   System.out.println("==>/member/update.do");
	   String  password  = passwordEncoder.encode(member.getPassword());	   
	   member.setPassword(password);	   
	   memberRepo.save(member);
	   	
	return "redirect:/member/list.do";
	}
	
	@GetMapping("login.do")
	String login() {
	   
	  return "member/login";
	}
	
	@GetMapping("loginSuccess.do")
	String loginSuccess() {
	   
	  return "member/success";
	}
	
	@GetMapping("accessDenied.do")
	String accessDenied() {
	   
	  return "member/accessDenied";
	}
		
	
}
