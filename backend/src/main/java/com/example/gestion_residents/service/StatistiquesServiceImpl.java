package com.example.gestion_residents.service;

import com.example.gestion_residents.model.*;
import com.example.gestion_residents.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatistiquesServiceImpl implements StatistiquesService {

    @Autowired
    private StatistiquesRepository statistiquesRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private PaimentRepository paimentRepository;

    @Autowired
    private TechnicienRepository technicienRepository;

   

    @Override
    public Statistiques createStatistiques(Statistiques statistiques) {
        return statistiquesRepository.save(statistiques);
    }

    @Override
    public List<Statistiques> getAllStatistiques() {
        return statistiquesRepository.findAll();
    }

    @Override
    public Optional<Statistiques> getStatistiquesById(Long id) {
        return statistiquesRepository.findById(id);
    }

    @Override
    public Statistiques updateStatistiques(Long id, Statistiques statistiquesDetails) {
        Optional<Statistiques> statistiquesOptional = statistiquesRepository.findById(id);
        if (statistiquesOptional.isPresent()) {
            Statistiques statistiques = statistiquesOptional.get();
            statistiques.setTauxOccupation(statistiquesDetails.getTauxOccupation());
            statistiques.setPaiementsEnRetard(statistiquesDetails.getPaiementsEnRetard());
            statistiques.setIncidentsEnCours(statistiquesDetails.getIncidentsEnCours());
            statistiques.setNombreTechniciens(statistiquesDetails.getNombreTechniciens());
            statistiques.setNombreResidents(statistiquesDetails.getNombreResidents());
            statistiques.setProfitLocations(statistiquesDetails.getProfitLocations());
            return statistiquesRepository.save(statistiques);
        } else {
            throw new RuntimeException("Statistiques not found with id " + id);
        }
    }

    @Override
    public void deleteStatistiques(Long id) {
        statistiquesRepository.deleteById(id);
    }

    @Override
    public String genererRapport(Long id) {
        Optional<Statistiques> statistiquesOptional = statistiquesRepository.findById(id);
        if (statistiquesOptional.isPresent()) {
            return statistiquesOptional.get().genererRapport();
        } else {
            throw new RuntimeException("Statistiques not found with id " + id);
        }
    }

    @Override
    public Statistiques calculerStatistiquesGlobales() {
        List<Chambre> chambres = chambreRepository.findAll();
        List<Paiment> paiements = paimentRepository.findAll();
        List<Technicien> techniciens = technicienRepository.findAll();
        //List<Resident> residents = residentRepository.findAll();

        int totalChambres = chambres.size();
        int chambresOccupees = (int) chambres.stream().filter(chambre -> chambre.getEtat() == ChambreEtat.OCCUPEE).count();
        int paiementsEnRetard = (int) paiements.stream().filter(paiment -> paiment.isEnRetard()).count();
        int incidentsEnCours = 0; // Vous pouvez ajouter la logique pour calculer les incidents en cours
        double profitLocations = paiements.stream().filter(paiment -> paiment.getEtat() == EtatPaiment.PAYE).mapToDouble(Paiment::getMontant).sum();

        Statistiques statistiques = new Statistiques();
        statistiques.setTauxOccupation((chambresOccupees * 100) / totalChambres);
        statistiques.setPaiementsEnRetard(paiementsEnRetard);
        statistiques.setIncidentsEnCours(incidentsEnCours);
        statistiques.setNombreTechniciens(techniciens.size());
        //statistiques.setNombreResidents(residents.size());
        statistiques.setProfitLocations(profitLocations);

        return statistiques;
    }
}
