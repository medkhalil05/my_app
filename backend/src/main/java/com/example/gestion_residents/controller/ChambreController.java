package com.example.gestion_residents.controller;

import com.example.gestion_residents.model.Chambre;
import com.example.gestion_residents.model.ChambreEtat;
import com.example.gestion_residents.service.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chambres")
public class ChambreController {

    @Autowired
    private ChambreService chambreService;

    @PostMapping
    public ResponseEntity<Chambre> createChambre(@RequestBody Chambre chambre) {
        Chambre newChambre = chambreService.createChambre(chambre);
        return ResponseEntity.ok(newChambre);
    }

    @GetMapping
    public ResponseEntity<List<Chambre>> getAllChambres() {
        List<Chambre> chambres = chambreService.getAllChambres();
        return ResponseEntity.ok(chambres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Long id) {
        Optional<Chambre> chambre = chambreService.getChambreById(id);
        return chambre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable Long id, @RequestBody Chambre chambreDetails) {
        try {
            Chambre updatedChambre = chambreService.updateChambre(id, chambreDetails);
            return ResponseEntity.ok(updatedChambre);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChambre(@PathVariable Long id) {
        try {
            chambreService.deleteChambre(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/disponibilite")
    public ResponseEntity<List<Chambre>> getChambresByDisponibilite(@RequestParam Boolean disponibilite) {
        return ResponseEntity.ok(chambreService.getChambresByDisponibilite(disponibilite));
    }

    @GetMapping("/etat")
    public ResponseEntity<List<Chambre>> getChambresByEtat(@RequestParam ChambreEtat etat) {
        return ResponseEntity.ok(chambreService.getChambresByEtat(etat));
    }

    @PutMapping("/{email}/assign-chambre/{chambreId}")
    public ResponseEntity<Boolean> assignChambre(@PathVariable String email, @PathVariable Long chambreId) {
        return ResponseEntity.ok(chambreService.assignChambre(email, chambreId));
    }

    @PutMapping("/desassign-chambre/{chambreId}")
    public ResponseEntity<Boolean> desassignChambre(@PathVariable Long chambreId) {
        return ResponseEntity.ok(chambreService.desassignChambre(chambreId));
    }
}