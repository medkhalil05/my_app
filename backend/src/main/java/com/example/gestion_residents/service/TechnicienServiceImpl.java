package com.example.gestion_residents.service;

import com.example.gestion_residents.model.Technicien;
import com.example.gestion_residents.repository.TechnicienRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicienServiceImpl implements TechnicienService {

    @Autowired
    private TechnicienRepository technicienRepository;

    @Override
    public Technicien createTechnicien(Technicien technicien) {
        return technicienRepository.save(technicien);
    }

    @Override
    public Technicien updateTechnicien(float id, Technicien technicienDetails) {
        Optional<Technicien> technicienOptional = technicienRepository.findById(id);
        if (technicienOptional.isPresent()) {
            Technicien technicien = technicienOptional.get();
            technicien.setNom(technicienDetails.getNom());
            technicien.setPrenom(technicienDetails.getPrenom());
           
            technicien.setTelephone(technicienDetails.getTelephone());
            return technicienRepository.save(technicien);
        } else {
            throw new RuntimeException("Technicien non trouvé pour l'id :: " + id);
        }
    }

    @Override
    public void deleteTechnicien(float id) {
        technicienRepository.deleteById(id);
    }

    @Override
    public Technicien getTechnicienById(float id) {
        return technicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technicien non trouvé pour l'id :: " + id));
    }

    @Override
    public List<Technicien> getAllTechniciens() {
        return technicienRepository.findAll();
    }
}