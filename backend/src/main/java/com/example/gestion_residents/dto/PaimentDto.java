package com.example.gestion_residents.dto;
import java.util.Date;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PaimentDto {
     private String nom;
     private String email;
     private Date date;
     private float montant;
    private String numéroChambre;

}
