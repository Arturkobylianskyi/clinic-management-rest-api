package com.artur.clinicmanagmentapi.service;

import com.artur.clinicmanagmentapi.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PatientService {
    Patient save(Patient patient);

    Patient findById(int id);

    List<Patient> findAll();

    void deleteById(int id);

}
