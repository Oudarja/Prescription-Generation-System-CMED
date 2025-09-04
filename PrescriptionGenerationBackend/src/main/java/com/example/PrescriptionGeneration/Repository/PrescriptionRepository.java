package com.example.PrescriptionGeneration.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.PrescriptionGeneration.Model.Prescription;

public interface PrescriptionRepository extends JpaRepository <Prescription, Long>
{
    Optional<Prescription> findById(Long id);
    List<Prescription> findAll();
    void deleteById(Long id);
    // Group prescriptions by day and count
    // custom query by @Query annotation
    // Object[0] = prescriptionDate, Object[1] = count
    @Query("SELECT p.prescriptionDate, COUNT(p) FROM Prescription p GROUP BY p.prescriptionDate ORDER BY p.prescriptionDate")
    List<Object[]> getDayWisePrescriptionCount();
     
    // Find prescriptions within a date range
    List<Prescription> findByPrescriptionDateBetween(LocalDate start, LocalDate end);
}
