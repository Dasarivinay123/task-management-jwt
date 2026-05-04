package com.vinay.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinay.dto.LogInDTO;
import com.vinay.dto.UsersDTO;
import com.vinay.payload.ApiResponse;
import com.vinay.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<Map<String, Object>>> createUser(
	        @RequestBody UsersDTO usersDto) {

	    ApiResponse<Map<String, Object>> response =
	            userService.createUser(usersDto);

	    return ResponseEntity
	            .status(response.getStatusCode())
	            .body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Map<String, Object>>> loginUser(
	        @RequestBody LogInDTO logInDTO) {

	    ApiResponse<Map<String, Object>> response =
	            userService.verify(logInDTO);

	    return ResponseEntity
	            .status(response.getStatusCode())
	            .body(response);
	}
	
}
