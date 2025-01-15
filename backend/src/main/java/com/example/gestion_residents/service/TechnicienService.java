package com.example.gestion_residents.service;

import com.example.gestion_residents.model.Technicien;

import java.util.List;

public interface TechnicienService {

    Technicien createTechnicien(Technicien technicien);

    Technicien updateTechnicien(float id, Technicien technicienDetails);

    void deleteTechnicien(float id);

    Technicien getTechnicienById(float id);

    List<Technicien> getAllTechniciens();
}