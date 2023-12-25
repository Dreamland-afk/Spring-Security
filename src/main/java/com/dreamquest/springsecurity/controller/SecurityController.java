package com.dreamquest.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String loginForm()
	{
		return "login-page";
	}
	
	@PostMapping("/login-processing")
	public String loginProcessing(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("Inside processing login form");
		
		return "redirect:/success";

	}
	
	@GetMapping("/success")
	public String successPage() {
		
		return "success";
	}
}
