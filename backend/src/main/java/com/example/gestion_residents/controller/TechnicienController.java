package com.example.gestion_residents.controller;

import com.example.gestion_residents.model.Technicien;
import com.example.gestion_residents.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/techniciens")
public class TechnicienController {

    @Autowired
    private TechnicienService technicienService;

    @PostMapping
    public ResponseEntity<Technicien> createTechnicien(@RequestBody Technicien technicien) {
        return ResponseEntity.ok(technicienService.createTechnicien(technicien));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technicien> updateTechnicien(@PathVariable Long id, @RequestBody Technicien technicienDetails) {
        return ResponseEntity.ok(technicienService.updateTechnicien(id, technicienDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicien(@PathVariable Long id) {
        technicienService.deleteTechnicien(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technicien> getTechnicienById(@PathVariable Long id) {
        return ResponseEntity.ok(technicienService.getTechnicienById(id));
    }

    @GetMapping
    public ResponseEntity<List<Technicien>> getAllTechniciens() {
        return ResponseEntity.ok(technicienService.getAllTechniciens());
    }
}