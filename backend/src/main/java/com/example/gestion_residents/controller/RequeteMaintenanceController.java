package com.example.gestion_residents.controller;

import com.example.gestion_residents.model.EtatRequete;
import com.example.gestion_residents.model.RequeteMaintenance;
import com.example.gestion_residents.service.RequeteMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requetes-maintenance")
public class RequeteMaintenanceController {

    @Autowired
    private RequeteMaintenanceService requeteMaintenanceService;

    @PostMapping
    public ResponseEntity<RequeteMaintenance> createRequeteMaintenance(@RequestBody RequeteMaintenance requeteMaintenance) {
        return ResponseEntity.ok(requeteMaintenanceService.createRequeteMaintenance(requeteMaintenance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequeteMaintenance> updateRequeteMaintenance(@PathVariable Long id, @RequestBody RequeteMaintenance requeteMaintenanceDetails) {
        return ResponseEntity.ok(requeteMaintenanceService.updateRequeteMaintenance(id, requeteMaintenanceDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequeteMaintenance(@PathVariable Long id) {
        requeteMaintenanceService.deleteRequeteMaintenance(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequeteMaintenance> getRequeteMaintenanceById(@PathVariable Long id) {
        return ResponseEntity.ok(requeteMaintenanceService.getRequeteMaintenanceById(id));
    }

    @GetMapping
    public ResponseEntity<List<RequeteMaintenance>> getAllRequeteMaintenances() {
        return ResponseEntity.ok(requeteMaintenanceService.getAllRequeteMaintenances());
    }

    @PutMapping("/{id}/assign-technicien/{technicienId}")
    public ResponseEntity<RequeteMaintenance> assignTechnicien(@PathVariable Long id, @PathVariable Long technicienId) {
        return ResponseEntity.ok(requeteMaintenanceService.assignTechnicien(id, technicienId));
    }

    @GetMapping("/etat")
    public ResponseEntity<List<RequeteMaintenance>> getRequetesByEtat(@RequestParam EtatRequete etat) {
        return ResponseEntity.ok(requeteMaintenanceService.getRequetesByEtat(etat));
    }
}