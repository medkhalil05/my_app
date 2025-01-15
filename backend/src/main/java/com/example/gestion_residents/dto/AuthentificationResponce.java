package com.example.gestion_residents.dto;

import com.example.gestion_residents.model.Role;

import lombok.Data;

@Data
public class AuthentificationResponce {
    private String jwt;
    private Long id;
    private Role role;
    
}
