package com.example.PrescriptionGeneration.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PrescriptionGeneration.Model.Prescription;

public interface PrescriptionRepository extends JpaRepository <Prescription, Long>
{
    

}
