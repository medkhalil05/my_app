package com.example.gestion_residents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_residents.model.EtatPaiment;
import com.example.gestion_residents.model.Paiment;
import java.util.List;


public interface PaimentRepository extends JpaRepository<Paiment,Float>{
      
      List<Paiment> findByEtat(EtatPaiment etat);
}
