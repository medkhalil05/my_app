package com.example.gestion_residents.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_residents.dto.ResidentDto;
import com.example.gestion_residents.model.Role;
import com.example.gestion_residents.model.User;
import com.example.gestion_residents.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChambreService chambreService;
    

    public List<ResidentDto> getAllResidents() {
        List<User> list = userRepository.findAllByRole(Role.RESIDENT).orElse(null);
        return list.stream().map(user -> new ResidentDto(user,chambreService.findByResidentId(user.getId()))).collect(Collectors.toList());
       
            
    }

    
    
  
    
}
