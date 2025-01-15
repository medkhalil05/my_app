package com.example.gestion_residents.service;



import com.example.gestion_residents.model.EtatPaiment;
import com.example.gestion_residents.model.Paiment;
import com.example.gestion_residents.repository.PaimentRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaimentServiceImpl implements PaimentService {

    @Autowired
    private PaimentRepository paimentRepository;

    @Override
    public Paiment createPaiment(Paiment paiment) {
        return paimentRepository.save(paiment);
    }

    @Override
    public List<Paiment> getAllPaiments() {
        return paimentRepository.findAll();
    }

    @Override
    public Optional<Paiment> getPaimentById(float id) {
        return paimentRepository.findById(id);
    }

    @Override
    public Paiment updatePaiment(float id, Paiment paimentDetails) {
        Optional<Paiment> paimentOptional = paimentRepository.findById(id);
        if (paimentOptional.isPresent()) {
            Paiment paiment = paimentOptional.get();
            paiment.setMontant(paimentDetails.getMontant());
            paiment.setDatePaiement(paimentDetails.getDatePaiement());
            paiment.setResident(paimentDetails.getResident());
            // Update other fields as necessary
            return paimentRepository.save(paiment);
        } else {
            throw new RuntimeException("Paiment not found with id " + id);
        }
    }

    @Override
    public void deletePaiment(float id) {
        paimentRepository.deleteById(id);
    }

    @Override
    public byte[] generateReceipt(float id) {
        Optional<Paiment> paimentOptional = paimentRepository.findById(id);
        if (paimentOptional.isPresent()) {
            Paiment paiment = paimentOptional.get();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Receipt for Payment"));
            document.add(new Paragraph("Payment ID: " + paiment.getId()));
            document.add(new Paragraph("Resident ID: " + paiment.getResident().getId()));
            document.add(new Paragraph("Resident Name: " + paiment.getResident().getNom() + " " + paiment.getResident().getPrenom()));
            document.add(new Paragraph("Resident Email: " + paiment.getResident().getEmail()));
            document.add(new Paragraph("Amount: " + paiment.getMontant()));
            document.add(new Paragraph("Date: " + paiment.getDatePaiement()));

            document.close();

            return byteArrayOutputStream.toByteArray();
        } else {
            throw new RuntimeException("Paiment not found with id " + id);
        }
    }

    @Override
    public void sendPaymentReminder(float residentId) {
        // Logic to send email reminder
        // For simplicity, printing a message to the console
        System.out.println("Sending payment reminder to resident ID: " + residentId);
    }

    public List<Paiment> paimentsEnRetard(){
        return paimentRepository.findByEtat(EtatPaiment.EN_RETARD);
    }

    public List<Paiment> paimentsEff(){
        return paimentRepository.findByEtat(EtatPaiment.PAYE);
    }

    public List<Paiment> paimentsEnCour(){
        return paimentRepository.findByEtat(EtatPaiment.EN_ATTENTE);
    }
}