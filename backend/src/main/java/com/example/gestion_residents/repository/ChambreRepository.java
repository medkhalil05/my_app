package com.example.gestion_residents.repository;

import com.example.gestion_residents.model.Chambre;
import com.example.gestion_residents.model.ChambreEtat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    List<Chambre> findByDisponibilite(Boolean disponibilite);
    List<Chambre> findByEtat(ChambreEtat etat);
    Chambre findByResidentId(Long residentId);
    Optional<Chambre> getChambreById(Long chambrfeId);



}