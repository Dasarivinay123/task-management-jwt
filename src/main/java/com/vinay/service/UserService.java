package com.vinay.service;

import java.util.Map;

import com.vinay.dto.LogInDTO;
import com.vinay.dto.UsersDTO;
import com.vinay.payload.ApiResponse;

public interface UserService {

	ApiResponse<Map<String, Object>> createUser(UsersDTO usersDto);

	ApiResponse<Map<String, Object>> verify(LogInDTO logInDTO);

	
}
