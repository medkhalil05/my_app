package com.example.gestion_residents.dto;


import com.example.gestion_residents.model.Chambre;
import com.example.gestion_residents.model.Role;
import com.example.gestion_residents.model.User;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResidentDto {
 
   
   
    private Long id;
    private String email;
    private String nom;
    private Role role;
    private String prenom;
    private Chambre chambre;

    public ResidentDto(User user,Chambre chambre) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nom = user.getNom();
        this.role = user.getRole();
        this.prenom = user.getPrenom();
        this.chambre = chambre;
    }
}
