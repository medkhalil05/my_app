package com.example.gestion_residents.service;

import com.example.gestion_residents.model.Paiment;

import java.util.List;
import java.util.Optional;

public interface PaimentService {
    Paiment createPaiment(Paiment paiment);
    List<Paiment> getAllPaiments();
    Optional<Paiment> getPaimentById(float id);
    Paiment updatePaiment(float id, Paiment paimentDetails);
    void deletePaiment(float id);
    List<Paiment> paimentsEnRetard();
    List<Paiment> paimentsEff();
    List<Paiment> paimentsEnCour();
    
    // Méthode pour générer un reçu de paiement
    byte[] generateReceipt(float id);

    // Méthode pour envoyer des notifications de rappel de paiement
    void sendPaymentReminder(float residentId);
}