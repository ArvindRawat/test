package com.arvind.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arvind.entity.ConsumerLoginStage;
import com.arvind.security.CustomUserDetailsService;
import com.arvind.security.JwtTokenUtil;
import com.arvind.security.model.CustomUserDetail;
import com.arvind.service.LoginService;
import com.arvind.utils.CustomResponse;


@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	
	@Autowired
	private JwtTokenUtil tokenUtil;
	
	String loggedIn="INFO1010";
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ResponseEntity<CustomResponse> login(HttpServletResponse res){
		System.out.println("In login method");
		String userEmail="arvind.rawat@kelltontech.com";
		ConsumerLoginStage user=loginService.userLogin(userEmail,"rawat");
		if(user!=null){
			CustomUserDetail customUserDetail=customUserDetailService.loadUserByUsername(userEmail);
			if(customUserDetail!=null){
				String token=tokenUtil.generateToken(customUserDetail);
				System.out.println("Token is "+token);
				res.setHeader("Authorization",token);
			}
		}
		return new ResponseEntity<CustomResponse>(new CustomResponse(loggedIn), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/personalDetails", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> updatePersonalDetails() {
		System.out.println("In personalDetails method");
		//String email = customUser.getUsername();
		//System.out.println("Email is "+email);
		return new ResponseEntity<CustomResponse>(new CustomResponse(loggedIn), HttpStatus.OK);
	}

}
