package com.vinay.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinay.dto.LogInDTO;
import com.vinay.dto.UsersDTO;
import com.vinay.entity.Users;
import com.vinay.exception.UserNotFoundException;
import com.vinay.repository.UserRepository;
import com.vinay.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtService;
    
    @Override
    public UsersDTO createUser(UsersDTO usersDto) {

        
        Users user = usersDtoToEntity(usersDto);
        user.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        Users savedUser = userRepository.save(user);
        return entityTousersDto(savedUser);
    }

    private Users usersDtoToEntity(UsersDTO usersDto) {
        Users user = new Users();
        user.setName(usersDto.getName());
        user.setEmail(usersDto.getEmail());
        user.setPassword(usersDto.getPassword());
        return user;
    }

    private UsersDTO entityTousersDto(Users user) {
        UsersDTO dto = new UsersDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());//do not expose password
        return dto;
    }

    @Override
    public String verify(LogInDTO logInDTO) {
        Authentication authenticate = authenticationManager
        		.authenticate( 
        			new UsernamePasswordAuthenticationToken(
        					logInDTO.getEmail(), logInDTO.getPassword())
        			);
        
        if (authenticate.isAuthenticated()) {
			return jwtService.generateToken(logInDTO);
		} else {
			return "Failure";
		}
    }
    
}