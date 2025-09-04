package com.example.PrescriptionGeneration.Controller;
import com.example.PrescriptionGeneration.Dto.PrescriptionRequest;
import com.example.PrescriptionGeneration.Model.Prescription;
import com.example.PrescriptionGeneration.Services.PrescriptionService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


@PostMapping("/create-prescription")
public ResponseEntity<Prescription> createPrescription(@Valid @RequestBody PrescriptionRequest prescriptionRequest) {
    Prescription saved = prescriptionService.savePrescription(prescriptionRequest);
    return ResponseEntity.ok(saved);
}

// Update prescription

 @PutMapping("/edit/{id}")
public ResponseEntity<Prescription> updatePrescription(@PathVariable Long id, @Valid @RequestBody Prescription prescription)
{
        Prescription updated = prescriptionService.updatePrescription(id, prescription);
        return ResponseEntity.ok(updated);
}

// Delete prescription
 @DeleteMapping("/delete/{id}")
public ResponseEntity<Void> deletePrescription(@PathVariable Long id) 
{
    prescriptionService.deletePrescription(id);
    // HTTP 204 No Content response.
    return ResponseEntity.noContent().build();
}

// Get all prescriptions
    @GetMapping("/fetch-all-prescriptions")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    // Get day-wise prescription count
    @GetMapping("/report/daywise")
    public ResponseEntity<List<Object[]>> getDayWiseReport() {
        List<Object[]> report = prescriptionService.getDayWisePrescriptionCount();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/fetch-all-by-month")
    public ResponseEntity<List<Prescription>> getPrescriptionsByMonth(
            @RequestParam int year,
            @RequestParam int month) {
    List<Prescription> prescriptions = prescriptionService.getPrescriptionsByMonth(year, month);
    return ResponseEntity.ok(prescriptions);
}

}
