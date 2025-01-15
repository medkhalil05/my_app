package com.example.gestion_residents.service;

import com.example.gestion_residents.model.EtatRequete;
import com.example.gestion_residents.model.RequeteMaintenance;

import java.util.List;

public interface RequeteMaintenanceService {

    RequeteMaintenance createRequeteMaintenance(RequeteMaintenance requeteMaintenance);

    RequeteMaintenance updateRequeteMaintenance(float id, RequeteMaintenance requeteMaintenanceDetails);

    void deleteRequeteMaintenance(float id);

    RequeteMaintenance getRequeteMaintenanceById(float id);

    List<RequeteMaintenance> getAllRequeteMaintenances();

    RequeteMaintenance assignTechnicien(float id, Long technicienId);

     List<RequeteMaintenance> getRequetesByEtat(EtatRequete etat);

}