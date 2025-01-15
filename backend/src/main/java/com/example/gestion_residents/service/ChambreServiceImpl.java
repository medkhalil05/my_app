package com.example.gestion_residents.service;

import com.example.gestion_residents.model.Chambre;
import com.example.gestion_residents.model.ChambreEtat;
import com.example.gestion_residents.model.EtatPaiment;
import com.example.gestion_residents.model.Paiment;
import com.example.gestion_residents.model.User;
import com.example.gestion_residents.repository.ChambreRepository;
import com.example.gestion_residents.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChambreServiceImpl implements ChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private UserRepository residentRepository;

    @Autowired
    private PaimentService paimentService;

    


    @Override
    public Chambre createChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Optional<Chambre> getChambreById(Long id) {
        return chambreRepository.findById(id);
    }

    @Override
    public Chambre updateChambre(Long id, Chambre chambreDetails) {
        Optional<Chambre> chambreOptional = chambreRepository.findById(id);
        if (chambreOptional.isPresent()) {
            Chambre chambre = chambreOptional.get();
            chambre.setTaille(chambreDetails.getTaille());
            chambre.setEquipements(chambreDetails.getEquipements());
            chambre.setDisponibilite(chambreDetails.getDisponibilite());
            // Update other fields as necessary
            return chambreRepository.save(chambre);
        } else {
            throw new RuntimeException("Chambre not found with id " + id);
        }
    }

    @Override
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }

    

    @Override
    public List<Chambre> getChambresByDisponibilite(Boolean disponibilite) {
        return chambreRepository.findByDisponibilite(disponibilite);
    }

    @Override
    public List<Chambre> getChambresByEtat(ChambreEtat etat) {
        return chambreRepository.findByEtat(etat);
    }

    @Override
    public Chambre findByResidentId(Long id_resident) {
        return chambreRepository.findByResidentId(id_resident);
    }

     @Override
    public Boolean assignChambre(String email, Long chambrfeId) {
        Optional<User> residentOptional = residentRepository.findFirstByEmail(email);
        Optional<Chambre> chambreOptional = chambreRepository.getChambreById(chambrfeId);

        if (residentOptional.isPresent() && chambreOptional.isPresent()) {
            User resident = residentOptional.get();
            Chambre chambre = chambreOptional.get();
            chambre.setResident(resident);
            chambre.setDisponibilite(false);
            chambre.setEtat(ChambreEtat.OCCUPEE);
            
            this.updateChambre(chambrfeId, chambre);

            
           

            // Créer un paiement avec l'état "en attente"
            Paiment paiment = new Paiment();
            paiment.setMontant(chambre.getPrix()); // Utiliser le prix de la chambre
            paiment.setDatePaiement(new Date());
            paiment.setEtat(EtatPaiment.EN_ATTENTE);
            paiment.setResident(resident);
            paiment.setChambre(chambre);
            paimentService.createPaiment(paiment);

            /*emailService.sendEmail(
                resident.getEmail(),
                "Rappel de paiement",
                "Veuillez effectuer le paiement pour la chambre " + chambre.getNumero() + "."
            );*/

            return true;
        } else {
            
            throw new RuntimeException("Resident or Chambre not found with provided ids");
            
        }
    }

    @Override
    public Boolean desassignChambre(Long chambreId) {
        Optional<Chambre> chambreOptional = chambreRepository.getChambreById(chambreId);
        if (chambreOptional.isPresent()) {
            Chambre chambre = chambreOptional.get();
            chambre.setResident(null);
            chambre.setDisponibilite(true);
            chambre.setEtat(ChambreEtat.DISPONIBLE);
            this.updateChambre(chambreId, chambre);
            return true;
        } else {
            throw new RuntimeException("Chambre not found with id " + chambreId);
        }
    }
}