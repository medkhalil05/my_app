package com.example.gestion_residents.service;

import com.example.gestion_residents.model.Chambre;
import com.example.gestion_residents.model.ChambreEtat;

import java.util.List;
import java.util.Optional;

public interface ChambreService {
    Chambre createChambre(Chambre chambre);
    List<Chambre> getAllChambres();
    Optional<Chambre> getChambreById(Long id);
    Chambre updateChambre(Long id, Chambre chambreDetails);
    void deleteChambre(Long id);
    List<Chambre> getChambresByDisponibilite(Boolean disponibilite);
    List<Chambre> getChambresByEtat(ChambreEtat etat);
    Chambre findByResidentId(Long id_resident);
    public Boolean assignChambre(String email, Long chambrfeId);
    public Boolean desassignChambre(Long chambreId);
}