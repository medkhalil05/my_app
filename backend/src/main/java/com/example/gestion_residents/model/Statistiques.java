package com.example.gestion_residents.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "statistiques")
public class Statistiques {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int tauxOccupation;

    @Column(nullable = false)
    private int paiementsEnRetard;

    @Column(nullable = false)
    private int incidentsEnCours;

    @Column(nullable = false)
    private int nombreTechniciens;

    @Column(nullable = false)
    private int nombreResidents;

    @Column(nullable = false)
    private double profitLocations; // Ajout du champ profit des locations

    // Méthode pour générer un rapport statistique
    public String genererRapport() {
        return String.format(
            "Rapport statistique :\n" +
            "- Taux d'occupation : %d%%\n" +
            "- Paiements en retard : %d\n" +
            "- Incidents en cours : %d\n" +
            "- Nombre de techniciens : %d\n" +
            "- Nombre de résidents : %d\n" +
            "- Profit des locations : %.2f",
            tauxOccupation,
            paiementsEnRetard,
            incidentsEnCours,
            nombreTechniciens,
            nombreResidents,
            profitLocations
        );
    }
}
