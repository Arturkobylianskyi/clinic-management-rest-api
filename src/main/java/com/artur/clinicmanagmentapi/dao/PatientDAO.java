package com.artur.clinicmanagmentapi.dao;

import com.artur.clinicmanagmentapi.entity.Patient;

import java.util.List;

public interface PatientDAO {
    Patient save(Patient patient);

    Patient findById(int id);

    List<Patient> findAll();

    void deleteById(int id);
}
