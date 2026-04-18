package com.vinay.service;

import com.vinay.dto.LogInDTO;
import com.vinay.dto.UsersDTO;

public interface UserService {

	UsersDTO createUser(UsersDTO usersDto);

	String verify(LogInDTO logInDTO);

	
}
