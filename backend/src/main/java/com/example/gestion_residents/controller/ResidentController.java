package com.example.gestion_residents.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_residents.dto.ResidentDto;
import com.example.gestion_residents.service.ResidentServiceImpl;


@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentServiceImpl residentService;

    @GetMapping
    public ResponseEntity<List<ResidentDto>> getAllResidents() {
        List<ResidentDto> residents = residentService.getAllResidents();
        return ResponseEntity.ok(residents);
    

}}
