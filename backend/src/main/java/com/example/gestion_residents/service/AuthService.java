package com.example.gestion_residents.service;

import com.example.gestion_residents.dto.SignupRequest;
import com.example.gestion_residents.dto.UserDto;

public interface AuthService {
       
     UserDto createUser(SignupRequest signupRequest);

}
