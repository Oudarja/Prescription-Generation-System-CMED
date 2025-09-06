package com.example.PrescriptionGeneration.Services;

import java.time.DateTimeException;
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
    try{
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
 catch(Exception e){
     System.err.println("Saving failed: " + e.getMessage());
    throw new RuntimeException("Failed to save prescription: " + e.getMessage());
 }

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

    } 
    catch (Exception e) {
        System.err.println("Update failed: " + e.getMessage());
        // Catch any unexpected errors
        throw new RuntimeException("Unexpected error while updating prescription", e);
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
        System.err.println("Deletion failed: " + e.getMessage());
        // You can log the exception if needed
        throw new RuntimeException("Failed to delete prescription: " + e.getMessage());
    }
}


public List<Prescription> getAllPrescriptions() 
{
    try {
        return (List<Prescription>) prescriptionRepository.findAll();
    } catch (Exception e) {
        System.err.println("Failed to fetch prescriptions: " + e.getMessage());
        throw new RuntimeException("Failed to fetch prescriptions: " + e.getMessage());
    }
}

public Prescription getPrescriptionById(Long id) 
{
    try {
        Optional<Prescription> prescription = prescriptionRepository.findById(id);
        if (!prescription.isPresent()) {
            throw new RuntimeException("Prescription not found with id: " + id);
        }
        return prescription.get();
    } catch (Exception e) {
        System.err.println("Failed to fetch prescription: " + e.getMessage());
        throw new RuntimeException("Failed to fetch prescription: " + e.getMessage());
    }
}

public List<Object[]> getDayWisePrescriptionCount() 
{
    try {
        return prescriptionRepository.getDayWisePrescriptionCount();
    } catch (Exception e) {
        System.err.println("Failed to fetch day-wise prescription count: " + e.getMessage());
        throw new RuntimeException("Failed to fetch day-wise prescription count: " + e.getMessage());
    }
}

   public List<Prescription> getPrescriptionsByRange(int year, int month, int day) {
    try {
        // First day of the given month
        LocalDate start = LocalDate.of(year, month, 1);

        // The specific day given
        LocalDate end = LocalDate.of(year, month, day);

        // Query prescriptions between start and end (inclusive)
        return prescriptionRepository.findByPrescriptionDateBetween(start, end);
    } catch (DateTimeException e) {
        throw new IllegalArgumentException("Invalid date: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Failed to fetch prescriptions by range: " + e.getMessage());
        throw new RuntimeException("Failed to fetch prescriptions by range: " + e.getMessage());
    }
}


}