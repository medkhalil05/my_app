
package com.example.gestion_residents.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chambres")
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , unique = true)
    private String numero; // Numéro de la chambre
    @Column(nullable = false)
    private long taille;
    @Column(nullable = false)
    private String equipements;
    @Column(nullable = false)
    private Boolean disponibilite;
    @Column(nullable = false)
    private ChambreEtat etat; // (occupée, disponible, en maintenance)
    @Column(nullable = false)
    private Double prix;
    @JoinColumn(name = "resident_id" )
    @OneToOne
    private User resident;
   

    
    

}