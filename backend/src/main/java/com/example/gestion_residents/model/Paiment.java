package com.example.gestion_residents.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paiements")
public class Paiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double montant;

    @Column(nullable = false)
    private Date datePaiement;

    @Column(nullable = false)
    private boolean enRetard;

    @Column(nullable = false)
    private EtatPaiment etat;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    @JsonIgnore
    private User resident;

    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;

    public void genererRecu() {
        // Logique pour générer un reçu
        System.out.println("Reçu généré pour le paiement de " + montant + " effectué le " + datePaiement);
    }
}
