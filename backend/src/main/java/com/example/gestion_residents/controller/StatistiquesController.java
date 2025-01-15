package com.example.gestion_residents.controller;

import com.example.gestion_residents.model.Statistiques;
import com.example.gestion_residents.service.StatistiquesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistiques")
public class StatistiquesController {

    @Autowired
    private StatistiquesService statistiquesService;

    @PostMapping
    public ResponseEntity<Statistiques> createStatistiques(@RequestBody Statistiques statistiques) {
        return ResponseEntity.ok(statistiquesService.createStatistiques(statistiques));
    }

    @GetMapping
    public ResponseEntity<List<Statistiques>> getAllStatistiques() {
        return ResponseEntity.ok(statistiquesService.getAllStatistiques());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statistiques> getStatistiquesById(@PathVariable Long id) {
        return statistiquesService.getStatistiquesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Statistiques> updateStatistiques(@PathVariable Long id, @RequestBody Statistiques statistiquesDetails) {
        return ResponseEntity.ok(statistiquesService.updateStatistiques(id, statistiquesDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistiques(@PathVariable Long id) {
        statistiquesService.deleteStatistiques(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/rapport")
    public ResponseEntity<String> genererRapport(@PathVariable Long id) {
        return ResponseEntity.ok(statistiquesService.genererRapport(id));
    }

    @GetMapping("/globales")
    public ResponseEntity<Statistiques> getStatistiquesGlobales() {
        return ResponseEntity.ok(statistiquesService.calculerStatistiquesGlobales());
    }
}
