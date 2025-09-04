package com.example.PrescriptionGeneration.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class PrescriptionRequest {

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

    private LocalDate nextVisitDate;

    // Getters and Setters

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public LocalDate getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(LocalDate nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

}
