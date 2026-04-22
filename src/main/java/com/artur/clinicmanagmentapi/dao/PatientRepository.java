package com.artur.clinicmanagmentapi.dao;

import com.artur.clinicmanagmentapi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // void
}
