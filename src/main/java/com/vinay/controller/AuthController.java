package com.vinay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinay.dto.LogInDTO;
import com.vinay.dto.UsersDTO;
import com.vinay.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDto) {
		UsersDTO dto = userService.createUser(usersDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody LogInDTO logInDTO) {
		
	    return userService.verify(logInDTO);
	}
	
}
