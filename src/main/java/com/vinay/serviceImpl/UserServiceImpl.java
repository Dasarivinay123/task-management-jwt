package com.vinay.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinay.controller.ResourceAlreadyExistsException;
import com.vinay.dto.LogInDTO;
import com.vinay.dto.UsersDTO;
import com.vinay.entity.Users;
import com.vinay.payload.ApiResponse;
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
    public ApiResponse<Map<String, Object>> createUser(UsersDTO usersDto) {

        if (userRepository.findByEmail(usersDto.getEmail()).isPresent()) {

        	throw new ResourceAlreadyExistsException("Email already exists");
        }

        Users user = usersDtoToEntity(usersDto);

        user.setPassword(
                passwordEncoder.encode(usersDto.getPassword())
        );

        Users savedUser = userRepository.save(user);

        Map<String, Object> data = new HashMap<>();
        data.put("id", savedUser.getId());
        data.put("name", savedUser.getName());
        data.put("email", savedUser.getEmail());

        return new ApiResponse<>(
                true,
                201,
                "User Registered Successfully",
                data
        );
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
    public ApiResponse<Map<String, Object>> verify(LogInDTO logInDTO) {

        try {

            Authentication authenticate =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    logInDTO.getEmail(),
                                    logInDTO.getPassword()
                            )
                    );

            if (authenticate.isAuthenticated()) {

                Users user = userRepository
                        .findByEmail(logInDTO.getEmail())
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

                String token = jwtService.generateToken(logInDTO);

                Map<String, Object> data = new HashMap<>();
                data.put("id", user.getId());
                data.put("name", user.getName());
                data.put("email", user.getEmail());
               // data.put("role", user.getRole());
                data.put("token", token);

                return new ApiResponse<>(
                        true,
                        200,
                        "Login Successful",
                        data
                );
            }

        } catch (Exception e) {

            return new ApiResponse<>(
                    false,
                    401,
                    "Invalid Email or Password",
                    null
            );
        }

        return new ApiResponse<>(
                false,
                401,
                "Invalid Email or Password",
                null
        );
    }
    
}