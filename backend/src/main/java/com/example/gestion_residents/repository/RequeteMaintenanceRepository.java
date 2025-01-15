package com.example.gestion_residents.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_residents.model.EtatRequete;
import com.example.gestion_residents.model.RequeteMaintenance;

public interface RequeteMaintenanceRepository extends JpaRepository<RequeteMaintenance,Float>{
    List<RequeteMaintenance> findByEtat(EtatRequete etat);
}
