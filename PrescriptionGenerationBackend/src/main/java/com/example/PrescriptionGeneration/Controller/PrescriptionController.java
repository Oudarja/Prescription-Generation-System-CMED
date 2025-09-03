package com.example.PrescriptionGeneration.Controller;
import com.example.PrescriptionGeneration.Dto.PrescriptionRequest;
import com.example.PrescriptionGeneration.Model.Prescription;
import com.example.PrescriptionGeneration.Services.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }
@PostMapping
public ResponseEntity<Prescription> createPrescription(@Valid @RequestBody PrescriptionRequest prescriptionRequest) {
    Prescription prescription = new Prescription();
    prescription.setPrescriptionDate(prescriptionRequest.getPrescriptionDate());
    prescription.setPatientName(prescriptionRequest.getPatientName());
    prescription.setPatientAge(prescriptionRequest.getPatientAge());
    prescription.setPatientGender(prescriptionRequest.getPatientGender());
    prescription.setDiagnosis(prescriptionRequest.getDiagnosis());
    prescription.setMedicines(prescriptionRequest.getMedicines());
    prescription.setNextVisitDate(prescriptionRequest.getNextVisitDate());

    Prescription saved = prescriptionService.savePrescription(prescription);
    return ResponseEntity.ok(saved);
}
}
