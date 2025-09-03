package com.example.PrescriptionGeneration.Services;

import org.springframework.stereotype.Service;

import com.example.PrescriptionGeneration.Model.Prescription;
import com.example.PrescriptionGeneration.Repository.PrescriptionRepository;

// Marks this class as a Spring service bean
@Service
public class PrescriptionService 
{
// The controller needs to talk to the PrescriptionService to perform operations (like saving prescriptions).
    private final PrescriptionRepository prescriptionRepository;

public PrescriptionService(PrescriptionRepository prescriptionRepository) 
{
    this.prescriptionRepository = prescriptionRepository;
}

public Prescription savePrescription(Prescription prescription) 
{
    return prescriptionRepository.save(prescription);
}

}