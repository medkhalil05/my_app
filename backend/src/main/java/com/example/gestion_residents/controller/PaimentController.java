package com.example.gestion_residents.controller;

import com.example.gestion_residents.model.Paiment;
import com.example.gestion_residents.service.PaimentService;
import com.itextpdf.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paiments")
public class PaimentController {

    @Autowired
    private PaimentService paimentService;

    @PostMapping
    public ResponseEntity<Paiment> createPaiment(@RequestBody Paiment paiment) {
        Paiment newPaiment = paimentService.createPaiment(paiment);
        return ResponseEntity.ok(newPaiment);
    }

    @GetMapping
    public ResponseEntity<List<Paiment>> getAllPaiments() {
        List<Paiment> paiments = paimentService.getAllPaiments();
        return ResponseEntity.ok(paiments);
    }

    @GetMapping("/enRetard")
    public ResponseEntity<List<Paiment>> getPaimentsEnretard(){
        List<Paiment> paiments = paimentService.paimentsEnRetard();
        return ResponseEntity.ok(paiments);
    }

    @GetMapping("/enAttente")
    public ResponseEntity<List<Paiment>> getPaimentsEnAttente(){
        List<Paiment> paiments = paimentService.paimentsEnCour();
        return ResponseEntity.ok(paiments);
    }

    @GetMapping("/paye")
    public ResponseEntity<List<Paiment>> getPaimentsPaye(){
        List<Paiment> paiments = paimentService.paimentsEff();
        return ResponseEntity.ok(paiments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiment> getPaimentById(@PathVariable Long id) {
        Optional<Paiment> paiment = paimentService.getPaimentById(id);
        return paiment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiment> updatePaiment(@PathVariable Long id, @RequestBody Paiment paimentDetails) {
        try {
            Paiment updatedPaiment = paimentService.updatePaiment(id, paimentDetails);
            return ResponseEntity.ok(updatedPaiment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaiment(@PathVariable Long id) {
        try {
            paimentService.deletePaiment(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/receipt")
    public ResponseEntity<Void> generateReceipt(@PathVariable Long id, HttpServletResponse response) throws java.io.IOException {
        byte[] receipt = paimentService.generateReceipt(id);
        if (receipt != null) {
            try {
                response.setContentType("application/pdf");
                response.getOutputStream().write(receipt);
                response.getOutputStream().flush();
                return ResponseEntity.ok().build();
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/reminder/{residentId}")
    public ResponseEntity<Void> sendPaymentReminder(@PathVariable Long residentId) {
        try {
            paimentService.sendPaymentReminder(residentId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}