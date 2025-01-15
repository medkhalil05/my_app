package com.example.gestion_residents.dto;


import com.example.gestion_residents.model.Role;
import com.example.gestion_residents.model.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class UserDto {
  
    private Long id;
    private String email;
    private String nom;
    private Role role;
    private String prenom;

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nom = user.getNom();
        this.role = user.getRole();
        this.prenom = user.getPrenom();
    }
}
