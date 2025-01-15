package com.example.gestion_residents.service;

import com.example.gestion_residents.model.Statistiques;

import java.util.List;
import java.util.Optional;

public interface StatistiquesService {

    Statistiques createStatistiques(Statistiques statistiques);

    List<Statistiques> getAllStatistiques();

    Optional<Statistiques> getStatistiquesById(Long id);

    Statistiques updateStatistiques(Long id, Statistiques statistiquesDetails);

    void deleteStatistiques(Long id);

    String genererRapport(Long id);

    Statistiques calculerStatistiquesGlobales(); // Nouvelle méthode pour calculer les statistiques globales
}
