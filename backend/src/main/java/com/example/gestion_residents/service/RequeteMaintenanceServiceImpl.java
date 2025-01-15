package com.example.gestion_residents.service;

import com.example.gestion_residents.model.EtatRequete;
import com.example.gestion_residents.model.RequeteMaintenance;
import com.example.gestion_residents.model.Technicien;
import com.example.gestion_residents.repository.RequeteMaintenanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequeteMaintenanceServiceImpl implements RequeteMaintenanceService {

    @Autowired
    private RequeteMaintenanceRepository requeteMaintenanceRepository;
    @Autowired
    private TechnicienService technicienService;
    @Override
    public RequeteMaintenance createRequeteMaintenance(RequeteMaintenance requeteMaintenance) {
        return requeteMaintenanceRepository.save(requeteMaintenance);
    }

    @Override
    public RequeteMaintenance updateRequeteMaintenance(float id, RequeteMaintenance requeteMaintenanceDetails) {
        Optional<RequeteMaintenance> requeteMaintenanceOptional = requeteMaintenanceRepository.findById(id);
        if (requeteMaintenanceOptional.isPresent()) {
            RequeteMaintenance requeteMaintenance = requeteMaintenanceOptional.get();
            requeteMaintenance.setDescription(requeteMaintenanceDetails.getDescription());
            requeteMaintenance.setDateCreation(requeteMaintenanceDetails.getDateCreation());
            requeteMaintenance.setEtat(requeteMaintenanceDetails.getEtat());
            requeteMaintenance.setChambre(requeteMaintenanceDetails.getChambre());
            requeteMaintenance.setTechnicienAssigne(requeteMaintenanceDetails.getTechnicienAssigne());
            requeteMaintenance.setResident(requeteMaintenance.getResident());
            return requeteMaintenanceRepository.save(requeteMaintenance);
        } else {
            throw new RuntimeException("Requête de maintenance non trouvée pour l'id :: " + id);
        }
    }

    @Override
    public void deleteRequeteMaintenance(float id) {
        requeteMaintenanceRepository.deleteById(id);
    }

    @Override
    public RequeteMaintenance getRequeteMaintenanceById(float id) {
        return requeteMaintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requête de maintenance non trouvée pour l'id :: " + id));
    }

    @Override
    public List<RequeteMaintenance> getAllRequeteMaintenances() {
        return requeteMaintenanceRepository.findAll();
    }

    @Override
    public RequeteMaintenance assignTechnicien(float id, Long technicienId) {
        Optional<RequeteMaintenance> requeteMaintenanceOptional = requeteMaintenanceRepository.findById(id);
        if (requeteMaintenanceOptional.isPresent()) {
            RequeteMaintenance requeteMaintenance = requeteMaintenanceOptional.get();
            // Logique pour assigner le technicien
            Technicien technicien = technicienService.getTechnicienById(technicienId);
            requeteMaintenance.setTechnicienAssigne(technicien);
            return requeteMaintenanceRepository.save(requeteMaintenance);
        } else {
            throw new RuntimeException("Requête de maintenance non trouvée pour l'id :: " + id);
        }
    }

     @Override
    public List<RequeteMaintenance> getRequetesByEtat(EtatRequete etat) {
        return requeteMaintenanceRepository.findByEtat(etat);
    }
}