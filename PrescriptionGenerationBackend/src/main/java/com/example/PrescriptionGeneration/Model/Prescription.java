package com.example.PrescriptionGeneration.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;


@Entity()
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Prescription date is required")
    private LocalDate prescriptionDate;

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @NotNull(message = "Patient age is required")
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 150, message = "Age seems invalid")
    private Integer patientAge;

    @NotBlank(message = "Patient gender is required")
    private String patientGender;

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

   @NotBlank(message = "Medicines are required")
    private String medicines;

    //  nextVisitDate is optional, so no @NotNull.
    // for some patient no need to next visit to doctore
    private LocalDate nextVisitDate;

    // getters and setters
public Long getId() {
    return id;
}

public LocalDate getPrescriptionDate() {
    return prescriptionDate;
}

public String getPatientName() {
    return patientName;
}

public Integer getPatientAge() {
    return patientAge;
}

public String getPatientGender() {
    return patientGender;
}

public String getDiagnosis() {
    return diagnosis;
}

public String getMedicines() {
    return medicines;
}

public LocalDate getNextVisitDate() {
    return nextVisitDate;
}

public void setId(Long id) {
    this.id = id;
}

public void setPrescriptionDate(LocalDate prescriptionDate) {
    this.prescriptionDate = prescriptionDate;
}

public void setPatientName(String patientName) {
    this.patientName = patientName;
}

public void setPatientAge(Integer patientAge) {
    this.patientAge = patientAge;
}

public void setPatientGender(String patientGender) {
    this.patientGender = patientGender;
}

public void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
}

public void setMedicines(String medicines) {
    this.medicines = medicines;
}

public void setNextVisitDate(LocalDate nextVisitDate) {
    this.nextVisitDate = nextVisitDate;
}


}