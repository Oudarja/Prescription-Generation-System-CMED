package com.example.PrescriptionGeneration.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PrescriptionGeneration.Dto.PrescriptionRequest;
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

public Prescription savePrescription(PrescriptionRequest prescriptionRequest) 
{
    Prescription prescription = new Prescription();
    prescription.setPrescriptionDate(prescriptionRequest.getPrescriptionDate());
    prescription.setPatientName(prescriptionRequest.getPatientName());
    prescription.setPatientAge(prescriptionRequest.getPatientAge());
    prescription.setPatientGender(prescriptionRequest.getPatientGender());
    prescription.setDiagnosis(prescriptionRequest.getDiagnosis());
    prescription.setMedicines(prescriptionRequest.getMedicines());
    prescription.setNextVisitDate(prescriptionRequest.getNextVisitDate());
    return prescriptionRepository.save(prescription);
}

// Edit prescription 
public Prescription updatePrescription(Long id, Prescription updatedPrescription) {
    try {

        // Optional<Prescription> acts like a container that may or may not contain a 
        // non-null Prescription value
        Optional<Prescription> existing = prescriptionRepository.findById(id);

        if (!existing.isPresent()) {
            throw new RuntimeException("Prescription not found with id: " + id);
        }

        // unwrap the Prescription object from the Optional prescription container
        Prescription existingPrescription = existing.get();

        // Update only fields you want editable
        existingPrescription.setPrescriptionDate(updatedPrescription.getPrescriptionDate());
        existingPrescription.setPatientName(updatedPrescription.getPatientName());
        existingPrescription.setPatientAge(updatedPrescription.getPatientAge());
        existingPrescription.setPatientGender(updatedPrescription.getPatientGender());
        existingPrescription.setDiagnosis(updatedPrescription.getDiagnosis());
        existingPrescription.setMedicines(updatedPrescription.getMedicines());
        existingPrescription.setNextVisitDate(updatedPrescription.getNextVisitDate());

        return prescriptionRepository.save(existingPrescription);

    } catch (RuntimeException ex) {
        // If prescription not found
        throw new RuntimeException("Error updating prescription: " + ex.getMessage(), ex);
    } catch (Exception ex) {
        // Catch any unexpected errors
        throw new RuntimeException("Unexpected error while updating prescription", ex);
    }
}


// Delete prescription
// Delete prescription with error handling
public void deletePrescription(Long id) {
    try {
        if (!prescriptionRepository.existsById(id)) {
            throw new RuntimeException("Prescription not found with id: " + id);
        }
        prescriptionRepository.deleteById(id);
    } catch (Exception e) {
        // You can log the exception if needed
        throw new RuntimeException("Failed to delete prescription: " + e.getMessage());
    }
}


public List<Prescription> getAllPrescriptions() 
{
    return (List<Prescription>) prescriptionRepository.findAll();
}

public List<Object[]> getDayWisePrescriptionCount() 
{
    return prescriptionRepository.getDayWisePrescriptionCount();
}

   public List<Prescription> getPrescriptionsByMonth(int year, int month) {
    LocalDate start = LocalDate.of(year, month, 1);
    LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
    return prescriptionRepository.findByPrescriptionDateBetween(start, end);
}

}