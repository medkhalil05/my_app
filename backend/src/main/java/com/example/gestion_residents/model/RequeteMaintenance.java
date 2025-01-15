package com.example.gestion_residents.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requete_maintenance")
public class RequeteMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatRequete etat;

    @OneToOne
    @JoinColumn(name="chambre_id")
    private Chambre chambre;

    @ManyToOne
    @JoinColumn(name = "technicien_id")
    @JsonIgnore
    private Technicien technicienAssigne;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private User resident;  
   
}
